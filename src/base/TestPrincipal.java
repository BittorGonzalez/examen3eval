package base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestPrincipal {

	@Test
	void testSolicitarPermiso() {
		
		Principal p = new Principal();
		
		Assertions.assertTrue(p.solicitarPermiso(90));
		Assertions.assertFalse(p.solicitarPermiso(20));
	}

}
