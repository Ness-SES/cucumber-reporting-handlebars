package net.reporting.cucumber.handlebars;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import net.reporting.cucumber.handlebars.helpers.Helpers;
import net.reporting.cucumber.helpers.AllFeatureReports;
import net.reporting.cucumber.json.models.Feature;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.google.gson.Gson;

public class ReportBuilder {

    private final String FEATURE_SUMMARY_REPORT = "featureSummaryReport";

    private final String FEATURE_OVERVIEW_REPORT = "featureOverviewReport";

    private final String REPORTS_SUMMARY_PATH;

    private final String REPORTS_OVERVIEW_PATH;

    private Gson gs = new Gson();

    private Handlebars bars = new Helpers(new Handlebars()).registerHelpers();

    private List<Feature> processedFeatures = null;

    public ReportBuilder(List<String> jsonReports) throws FileNotFoundException, IOException {
        REPORTS_SUMMARY_PATH = "cucumber-reports-with-handlebars/feature-reports/";
        REPORTS_OVERVIEW_PATH = "cucumber-reports-with-handlebars/";

        processedFeatures = prepareData(jsonReports);
    }

    private void writeFeatureSummaryReports() throws IOException {
        Template template = bars.compile(FEATURE_SUMMARY_REPORT);
        for (Feature feature : processedFeatures) {
            String generatedFeatureHtmlContent = template.apply(feature);
            // generatedFeatureSummaryReports.put(feature.getUniqueID(), generatedFeatureHtmlContent);
            FileUtils.writeStringToFile(new File(REPORTS_SUMMARY_PATH + feature.getUniqueID() + ".html"),
                generatedFeatureHtmlContent);
        }
    }

    private void writeFeatureOverviewReport() throws IOException {
        Template template = bars.compile(FEATURE_OVERVIEW_REPORT);
        AllFeatureReports allFeatures = new AllFeatureReports(processedFeatures);
        FileUtils.writeStringToFile(new File(REPORTS_OVERVIEW_PATH + "featuresOverview.html"),
            template.apply(allFeatures));
    }

    private List<Feature> prepareData(List<String> jsonReports) throws FileNotFoundException, IOException {
        List<Feature> processedFeatures = new ArrayList<>();
        for (String jsonReport : jsonReports) {
            String gson = IOUtils.toString(new FileInputStream(
                new File(jsonReport)));

            Feature[] features = gs.fromJson(gson, Feature[].class);
            for (Feature feature : features) {
                processedFeatures.add(feature.postProcess());
            }
        }
        return processedFeatures;
    }

    public void writeReportsOnDisk() throws IOException {
        writeFeatureSummaryReports();
        writeFeatureOverviewReport();
    }
}
