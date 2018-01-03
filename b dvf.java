package javahello;
import java.sql.*;

public class HelloWorldJDBCBatch {
  public static void main(String[] args) {
    Connection con = null;
    Statement stmt = null;
    try {
      // ドライバクラスをロード
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      // データベースへ接続
      con = DriverManager.getConnection("jdbc:odbc:helloworld", "", "");
      // 自動コミットモードを解除します
      con.setAutoCommit(false);
      // ステートメントオブジェクトを生成
      stmt = con.createStatement();
      
      /**処理１**/
      stmt.addBatch("INSERT INTO HELLO_WORLD_TABLE VALUES(5,'フランス語','Bonjour, tout le monde')");

      /**処理２**/
      stmt.addBatch("INSERT INTO HELLO_WORLD_TABLE VALUES(1,'ドイツ語','Hallo Welt')");
	
      // バッチを実行します
      stmt.executeBatch();

     con.commit();
    } catch (Exception e) {
      e.printStackTrace();
      try {
        // ロールバックします
        con.rollback();
      } catch (SQLException ex) {
        e.printStackTrace();
      }
    } finally {
      // データベースへの接続をクローズします
      try {
        if (stmt!=null) {
          stmt.close();
        }
        if (con!=null) {
          con.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}