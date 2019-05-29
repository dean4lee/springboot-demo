package demo.springboot.bean.bean;

/**
 * @author dean.lee
 */
public class Test1 {
    private Test test;

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "Test1{" +
                "test=" + test +
                '}';
    }
}
