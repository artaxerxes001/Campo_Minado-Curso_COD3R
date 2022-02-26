import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Teste {
    @Test
    void testarSeIgualDois() {
        int a = 1 + 1;
        assertEquals(2, a);
    }

    @Test
    void testarSeIgualTres() {
        int x = 2 + 10 - 7 - 2;
        assertEquals(3, x);
    }
}
