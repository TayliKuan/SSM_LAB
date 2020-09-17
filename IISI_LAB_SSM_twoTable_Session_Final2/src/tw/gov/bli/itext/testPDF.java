package tw.gov.bli.itext;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPHeaderCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

public class testPDF {
	public static  void test()  {
	       try {
	           create();
	           System.out.println("生成成功");
	       }catch (Exception ex){
	           System.out.println("檔案路徑錯誤或者許可權不夠");
	       }

	   }
	
	public static void main(String[] args) {
		test();
	}

	    private static void create() throws Exception {

	        // 建立一個文件（預設大小A4，邊距36, 36, 36, 36）
	        Document document = new Document();  
	        // 設定文件大小  
	        document.setPageSize(PageSize.A4);  
	        // 設定邊距，單位都是畫素，換算大約1釐米=28.33畫素  
	        document.setMargins(50, 50, 50, 50);
	        // 設定pdf生成的路徑
	        FileOutputStream fileOutputStream= new FileOutputStream("D:/demoaaa.pdf");
	        // 建立writer，通過writer將文件寫入磁碟
	        PdfWriter writer = PdfWriter.getInstance(document,fileOutputStream);
	        // demo
	        String title = "你好";
	        String content = "hello world";
	    
	        // 定義字型  
	        FontFactoryImp ffi = new FontFactoryImp();  
	        // 註冊全部預設字型目錄，windows會自動找fonts資料夾的，返回值為註冊到了多少字型  
	        ffi.registerDirectories();  
	        // 獲取字型，其實不用這麼麻煩，後面有簡單方法  
	        Font font = ffi.getFont("標楷體",BaseFont.IDENTITY_H,BaseFont.EMBEDDED, 12, Font.UNDEFINED, null);  
	    
	        // 開啟文件，只有開啟後才能往裡面加東西  
	        document.open();  
//	    
//	        // 設定作者  
//	        document.addAuthor("這是標楷體");
//	        // 設定建立者  
//	        document.addCreator("111");
//	        // 設定主題  
//	        document.addSubject("222");
//	        // 設定標題  
//	        document.addTitle("這是標楷體");
	    
	        // 增加一個段落  
	        document.add(new Paragraph(title, font));  
	        document.add(new Paragraph(content, font));  
	        document.add(new Paragraph("\n\r", font));  
	    
	        // 建立表格，5列的表格  
	        PdfPTable table = new PdfPTable(4);  
	        table.setTotalWidth(PageSize.A4.getWidth()- 100);  
	        table.setLockedWidth(true);  
	        // 建立頭  
	        PdfPHeaderCell header = new PdfPHeaderCell();  
	        header.addElement(new Paragraph(title, font));  
	        header.setColspan(4);  
	        table.addCell(header);  
	        // 新增內容  
	        table.addCell(new Paragraph("4444",font));
	        table.addCell(new Paragraph("這是標楷體",font));
	        table.addCell(new Paragraph("6666",font));
	        table.addCell(new Paragraph("7777",font));
	    
	        document.add(table);  
	        // 關閉文件，才能輸出  
	        document.close();  
	        writer.close();  
	   }  

}
