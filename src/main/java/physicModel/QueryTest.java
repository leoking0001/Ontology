package physicModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import java.io.FileInputStream;
import java.io.InputStreamReader;
/**
 * Created by xufuxiu on 2017/7/29.
 */
public class QueryTest
{
    //获得uri中的最后一个字符
     public static void main(String[] args)throws  Exception
      {
          FileInputStream file=new FileInputStream("F:/xusoft/intelliJ IDEA/Ontology/src/main/java/physicModel/physicOntology4.owl");
          InputStreamReader in=new InputStreamReader(file,"UTF-8");
          Model model= ModelFactory.createDefaultModel();
          model.read(in,null);
      }

}
