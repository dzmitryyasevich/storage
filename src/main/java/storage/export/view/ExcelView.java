package storage.export.view;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import storage.domain.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"export.xls\"");

        @SuppressWarnings("unchecked")
        List<Product> products = (List<Product>) model.get("products");

        // create excel xls sheet
        Sheet sheet = workbook.createSheet("Product Details");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.AQUA.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("id");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("name");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("brand");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("price");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("size");
        header.getCell(4).setCellStyle(style);
        header.createCell(5).setCellValue("quantity");
        header.getCell(5).setCellStyle(style);

        int rowCount = 1;

        for(Product product : products){
            Row userRow =  sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(product.getId());
            userRow.createCell(1).setCellValue(product.getName());
            userRow.createCell(2).setCellValue(product.getBrand().getName());
            userRow.createCell(3).setCellValue(product.getPrice());
            userRow.createCell(4).setCellValue(product.getSize());
            userRow.createCell(5).setCellValue(product.getQuantity());

            }

    }

}
