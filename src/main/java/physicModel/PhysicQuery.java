package physicModel;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import java.util.Iterator;
public class PhysicQuery
{

    public static void main(String[] args) 
    {
        //创建使用OWL语言的内存模型
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        //读取当前路径下的文件，加载模型.
        ontModel.read("file:/xusoft/intelliJ IDEA/Ontology/src/main/java/physicModel/physicOntology4.owl");
        //定义一个类作为等价类
        OntClass cls=ontModel.createClass(":物理概念");
        cls.addComment("the EquivalentClass of 物理概念...", "CN");
        // 通过完整的URI取得模型中的物理概念类
        OntClass oc=ontModel.getOntClass("http://www.semanticweb.org/administrator/ontologies/2017/3/untitled-ontology-4/physicModel/physicModel4.owl#物理概念");

        oc.addEquivalentClass(cls);
        // 迭代显示模型中的类，在迭代过程中完成各种操作
        for(Iterator i=ontModel.listClasses();i.hasNext();)
        {
            OntClass c=(OntClass)i.next();
            if (!c.isAnon()) 
            { // 如果不是匿名类，则打印类的名字
                System.out.print("Class");
                 // 获取类的URI并输出，在输出时对URI做了简化（将命名空间前缀省略）
                 System.out.println(c.getModel().getGraph().getPrefixMapping().
                     shortForm(c.getURI()));
                // 处理物理概念类
                 if (c.getLocalName().equals("物理概念")) 
                 { // 如果当前类是物理概念
                    System.out.println("  URI@" + c.getURI()); // 输出它的完整URI
                                    // 取得它的的等价类并打印
                    System.out.print("物理概念's EquivalentClass is " +
                             c.getEquivalentClass());
                    // 输出等价类的注释
                      System.out.println(" [comments:" + c.getEquivalentClass().getComment
                           ("CN")+"]");
                  }// 处理物理概念结束

            }
           
            // 迭代显示当前类的直接子类 
         for (Iterator it = c.listSubClasses(); it.hasNext();)
          {   
              System.out.print(" Class");
              OntClass sb = (OntClass) it.next();
              System.out.println(c.getModel().getGraph().getPrefixMapping().
              shortForm(c.getURI()) + "'s suberClass is "+ sb.getModel().
              getGraph().getPrefixMapping().shortForm(sb.getURI()));
          }// suber class ends
        }  
         
    }
}
