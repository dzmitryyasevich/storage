package storage.export.view;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import storage.domain.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class CsvView extends AbstractCsvView {


    @Override
    protected void buildCsvDocument(Map<String, Object> model, HttpServletRequest request, HttpServletResponse
            response) throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=\"export.csv\"");

        List<Product> products = (List<Product>) model.get("products");
        String[] header = {"id", "name", "brand", "price", "size", "quantity"};
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        csvWriter.writeHeader(header);

        for (Product product: products) {
            csvWriter.write(product, header);
        }
        csvWriter.close();

    }
}
