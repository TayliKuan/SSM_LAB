package tw.gov.bli.poi;

import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

public class testExcel {
	/** Excel 檔要存放的位置，假定在D盤JTest目錄下 */

	public static String outputFile = "D:/demo.xls";

	public static void main(String argv[]) {

		try {

			// 創建新的Excel 工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();

// 在Excel工作簿中建一工作表，其名為缺省值
// 如要新建一名為"效益指標"的工作表，其語句為：
// HSSFSheet sheet = workbook.createSheet("效益指標");

			HSSFSheet sheet = workbook.createSheet();

// 在索引0的位置創建行（最頂端的行）
			// 在指定的 Sheet 中從第一行開始建立 5 個 Row，每個 Row 包含 10 個 Cell
			
			HSSFRow row = sheet.createRow((short) 0);

//在索引0的位置創建單格（左上端）
			HSSFCell cell = row.createCell(0);
			HSSFCell cell2 = row.createCell(1);
			HSSFCell cell3 = row.createCell(3);
			HSSFCell cell4 = row.createCell(4);

// 定義單格為字串類型
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
// 在單格中輸入一些內容
			cell.setCellValue("1");
			cell2.setCellValue("2");
			cell3.setCellValue("3");
			cell4.setCellValue("4");
// 新建一輸出檔流
			FileOutputStream fOut = new FileOutputStream(outputFile);
// 把相應的Excel 工作簿存檔
			workbook.write(fOut);
			fOut.flush();
// 操作結束，關閉檔
			fOut.close();
			System.out.println("檔生成OK");

		} catch (Exception e) {
			System.out.println("已運行 xlCreate() : " + e);
		}
	}

}
