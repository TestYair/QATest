package javahello;
import java.sql.*;

public class HelloWorldJDBCBatch {
  public static void main(String[] args) {
    Connection con = null;
    Statement stmt = null;
    try {
      // �h���C�o�N���X�����[�h
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      // �f�[�^�x�[�X�֐ڑ�
      con = DriverManager.getConnection("jdbc:odbc:helloworld", "", "");
      // �����R�~�b�g���[�h���������܂�
      con.setAutoCommit(false);
      // �X�e�[�g�����g�I�u�W�F�N�g�𐶐�
      stmt = con.createStatement();
      
      /**�����P**/
      stmt.addBatch("INSERT INTO HELLO_WORLD_TABLE VALUES(5,'�t�����X��','Bonjour, tout le monde')");

      /**�����Q**/
      stmt.addBatch("INSERT INTO HELLO_WORLD_TABLE VALUES(1,'�h�C�c��','Hallo Welt')");
	
      // �o�b�`�����s���܂�
      stmt.executeBatch();

     con.commit();
    } catch (Exception e) {
      e.printStackTrace();
      try {
        // ���[���o�b�N���܂�
        con.rollback();
      } catch (SQLException ex) {
        e.printStackTrace();
      }
    } finally {
      // �f�[�^�x�[�X�ւ̐ڑ����N���[�Y���܂�
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