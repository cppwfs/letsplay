package io.spring.letsplay;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CanWePlayTests {

	@Test
	void basicTest() {
		CanWePlay canWePlay = new CanWePlay();
		assertThat(canWePlay.apply("34.0265,-84.3564")).isNotEmpty();
	}
	@Test
	void invalidCoordinateTest() {
		CanWePlay canWePlay = new CanWePlay();
		assertThat(canWePlay.apply("50,50")).isEqualTo("The Coordinates did not return a place we can get a forecast.  Sorry :-(");
	}

}
