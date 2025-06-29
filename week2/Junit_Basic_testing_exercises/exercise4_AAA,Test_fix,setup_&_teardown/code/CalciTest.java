import org.junit.*;
import static org.junit.Assert.*;
public class CalciTest {
    private Calculator calculator;
    @Before
    public void setUp() {
        calculator = new Calculator();
        System.out.println("Setup complete");    }
    @After
    public void tearDown() {
        calculator = null;
        System.out.println("Teardown complete");  }
    @Test
    public void testAddition() {
        int a = 2, b = 3,result = calculator.add(a, b);
        assertEquals(5, result);    }
    @Test
    public void testSubtraction() {
        int a = 5;
        int b = 3;
        int result = calculator.subtract(a, b);
        assertEquals(2, result);
    }
}

