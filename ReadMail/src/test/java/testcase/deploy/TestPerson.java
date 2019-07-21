package testcase.deploy;


import com.topica.maitrongnghia.main.config.SourceType;
import com.topica.maitrongnghia.main.deploy.WorkingMail;
import junit.framework.TestCase;
import org.junit.runner.JUnitCore;
import test.Person;

public class TestPerson extends TestCase {
        public TestPerson(String name) {
            super(name);
        }
        public  static void main(String[] args){
            JUnitCore.runClasses(TestPerson.class);
        }
        /**
            * Xac nhan rang name duoc the hien dung dinh dang
        */
        public void testGetFullName() {
            Person p = new Person("Aidan", "Burke");
            System.out.println(p.getFullName());
            assertEquals("Aidan Burke", p.getFullName());
            WorkingMail workingMail = new WorkingMail();
            workingMail.sendMail(SourceType.MAIL_RECEIVER,"ket qua test 1:  true"  );
        }
        /**
        * Xac nhan rang nulls da duoc xu ly chinh xac
        */
        public void testNullsInName() {
            Person  p = new Person(null, "Burke");
            assertEquals("? Burke", p.getFullName());
            p = new Person("Tanner", null);
            assertEquals("Tanner ?", p.getFullName());
            WorkingMail workingMail = new WorkingMail();
            workingMail.sendMail(SourceType.MAIL_RECEIVER,"ket qua test 2 true:" );
        }


    }