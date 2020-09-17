package tw.gov.bli.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class logTest {
	private static Logger logger = LoggerFactory.getLogger(logTest.class);

    public static void main(String[] args) {
        logger.trace("Trace message");
        logger.debug("Debug message");
        logger.info("Info message");
        logger.warn("Warn message");
        logger.error("Error message");
    }
}
