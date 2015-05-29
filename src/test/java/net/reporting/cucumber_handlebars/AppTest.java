package net.reporting.cucumber_handlebars;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

import net.reporting.cucumber.handlebars.ReportBuilder;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
    extends TestCase
{
    /**
     * Create the test case
     * @param testName name of the test case
     */
    public AppTest(String testName)
    {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     * @throws IOException
     */
    @SuppressWarnings({"unchecked", "unused"})
    public void testApp() throws IOException
    {
        String path =
            "C:\\Users\\p3700383\\Documents\\cucumber-reporting-handlebars\\src\\main\\resources\\ result.json";
        List<String> paths = new ArrayList<>();

        // ReportBuilder rep = new ReportBuilder();
        // rep.writeFeatureSummaryReports(paths);
        // rep.writeFeatureOverviewReports(paths);

        File dir = new File("C:\\Users\\p3700383\\Documents\\cucumber-reporting-handlebars\\src\\test\\resources\\");
        String[] extensions = new String[] {"json"};
        System.out.println("Getting all .txt and .jsp files in " + dir.getCanonicalPath()
            + " including those in subdirectories");

        List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
        for (File file : files) {
            paths.add(file.getAbsolutePath());
        }

        ReportBuilder rep = new ReportBuilder(paths);
        rep.writeReportsOnDisk();
        System.out.println("DONEEEEEEEEEEEEEEEEE");
    }
}
