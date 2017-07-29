package physicModel;

import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.db.RDFRDBException;
import com.hp.hpl.jena.rdf.model.*;

import java.io.*;
import java.sql.SQLException;
public class PhysicModel
{
public static final String strDriver="com.mysql.jdbc.Driver" ;
public static final String strURL="jdbc:mysql://127.0.0.1:3306/ontology_physic?useUnicode=true&characterEncoding=utf8";
public static final String strUser="root";
public static final String strPassWord="root";
public static final String strDB="MYSQL";
    public static void main(String[] args) throws SQLException
    {
        try
        {            
            DBConnection conn=new DBConnection(strURL,strUser,strPassWord,strDB);
            try
            {
                Class.forName(strDriver);
                System.out.println("驱动程序已经安装");
            }catch(ClassNotFoundException e)
            {
                System.out.println(e);
                System.out.println("Driver is not available...");
            }

            //读取owl文件并且存储到数据库
            ModelMaker maker=ModelFactory.createModelRDBMaker(conn);
            //创建一个默认模型，命名为physicOntology
            Model defModel=maker.createModel("physicOntology");
            FileInputStream read=null;
            try
            {
                File file = new File("F:/xusoft/intelliJ IDEA/Ontology/src/main/java/physicModel/physicOntology4.owl");
                 read=new FileInputStream(file);                
            } catch (FileNotFoundException e) 
            {
                e.printStackTrace();
                System.out.println("未找到要存储的本体文件，请检查文件地址和名称");                
            }
            InputStreamReader in=null;
            try
            {
                in=new InputStreamReader((FileInputStream)read,"UTF-8");              
            }catch(UnsupportedEncodingException e)
            {
                e.printStackTrace();
                System.out.println("已经将字节流文件转换为UTF-8编码");
            }
            defModel.read(in,null);
            try
            {
                in.close();
            }catch(IOException e)
            {
                e.printStackTrace();
                System.out.println("无法关闭字节流文件");
            }
            
            System.out.println("已经关闭字节流文件");
            defModel.commit();
            System.out.println("数据转换执行完毕，已经将本体文件存入数据库");
            try
            {
                conn.close();
            }catch(SQLException e)
            {
                e.printStackTrace();
                System.out.println("文件无法关闭");
            }
        }catch(RDFRDBException e)
        {
            System.out.println(e);
            System.out.println("exception occur...");
        }
    }
    
}
