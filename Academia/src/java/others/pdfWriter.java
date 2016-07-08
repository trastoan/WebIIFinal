/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import Entidades.Aluno;
import Entidades.Exercicios;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
/**
 *
 * @author Yuri
 */
public class pdfWriter {
    private static String FILE = System.getProperty("user.dir") + "\ficha.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
        Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
        Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
        Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
        Font.BOLD);



  // iText allows to add metadata to the PDF which can be viewed in your Adobe
  // Reader
  // under File -> Properties
  private static void addMetaData(Document document) {
    document.addTitle("My first PDF");
    document.addSubject("Using iText");
    document.addKeywords("Java, PDF, iText");
    document.addAuthor("Lars Vogel");
    document.addCreator("Lars Vogel");
  }
  public static void addTitlePageProfessor(Document document, String title, String user)
      throws DocumentException {
    Paragraph preface = new Paragraph();
    // We add one empty line
    addEmptyLine(preface, 1);
    // Lets write a big header
    preface.add(new Paragraph(title, catFont));

    addEmptyLine(preface, 1);
    // Will create: Report generated by: _name, _date
    preface.add(new Paragraph("Lista gerada por: " + user + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        smallBold));
    addEmptyLine(preface, 2);

    
    document.add(preface);
    addEmptyLine(preface, 3);
    // Start a new page
  }

  public static void addTitlePage(Document document, String title, String user)
      throws DocumentException {
    Paragraph preface = new Paragraph();
    // We add one empty line
    addEmptyLine(preface, 1);
    // Lets write a big header
    preface.add(new Paragraph(title, catFont));

    addEmptyLine(preface, 1);
    // Will create: Report generated by: _name, _date
    preface.add(new Paragraph("Ficha gerada por: " + user + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        smallBold));
    addEmptyLine(preface, 2);
    preface.add(new Paragraph("Ficha de Treino",
        smallBold));

    
    document.add(preface);
    addEmptyLine(preface, 3);
    // Start a new page
  }

  private static void addContent(Document document) throws DocumentException {
    Anchor anchor = new Anchor("First Chapter", catFont);
    anchor.setName("First Chapter");

    // Second parameter is the number of the chapter
    Chapter catPart = new Chapter(new Paragraph(anchor), 1);

    Paragraph subPara = new Paragraph("Subcategory 1", subFont);
    Section subCatPart = catPart.addSection(subPara);
    subCatPart.add(new Paragraph("Hello"));

    subPara = new Paragraph("Subcategory 2", subFont);
    subCatPart = catPart.addSection(subPara);
    subCatPart.add(new Paragraph("Paragraph 1"));
    subCatPart.add(new Paragraph("Paragraph 2"));
    subCatPart.add(new Paragraph("Paragraph 3"));

    // add a list
    createList(subCatPart);
    Paragraph paragraph = new Paragraph();
    addEmptyLine(paragraph, 5);
    subCatPart.add(paragraph);

    // add a table
//    createTableExercicios(subCatPart);

    // now add all this to the document
    document.add(catPart);

    // Next section
    anchor = new Anchor("Second Chapter", catFont);
    anchor.setName("Second Chapter");

    // Second parameter is the number of the chapter
    catPart = new Chapter(new Paragraph(anchor), 1);

    subPara = new Paragraph("Subcategory", subFont);
    subCatPart = catPart.addSection(subPara);
    subCatPart.add(new Paragraph("This is a very important message"));

    // now add all this to the document
    document.add(catPart);

  }
   public static void createTableExercicios(Document document, java.util.List<Exercicios> exercicios)
      throws BadElementException, DocumentException {
    PdfPTable table = new PdfPTable(3);

    // t.setBorderColor(BaseColor.GRAY);
    // t.setPadding(4);
    // t.setSpacing(4);
    // t.setBorderWidth(1);
    
    PdfPCell c1 = new PdfPCell(new Phrase("Exercicio"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);

    c1 = new PdfPCell(new Phrase("Descrição"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);

    c1 = new PdfPCell(new Phrase("Musculo"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);
    table.setHeaderRows(1);
    if(exercicios.size() < 1){
        table.addCell("Adicione Exercicios");
        table.addCell("Adicione Exercicios");
        table.addCell("Adicione Exercicios");
    }
    for(Exercicios exercicio:exercicios){
        table.addCell(exercicio.getNome());
        table.addCell(exercicio.getDescricao());
        table.addCell(exercicio.getMusculo());
    }

    document.add(table);

  }
  
  public static void createTableAlunos(Document document, java.util.List<Aluno> alunos)
      throws BadElementException, DocumentException {
    PdfPTable table = new PdfPTable(2);

    // t.setBorderColor(BaseColor.GRAY);
    // t.setPadding(4);
    // t.setSpacing(4);
    // t.setBorderWidth(1);
    
    PdfPCell c1 = new PdfPCell(new Phrase("Aluno"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);

    c1 = new PdfPCell(new Phrase("Email"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);

    table.setHeaderRows(1);
    if(alunos.size() < 1){
        table.addCell("Adicione Alunos");
        table.addCell("Adicione Alunos");
    }
    for(Aluno aluno:alunos){
        table.addCell(aluno.getNome());
        table.addCell(aluno.getEmail());
    }

    document.add(table);

  }

  private static void createList(Section subCatPart) {
    List list = new List(true, false, 10);
    list.add(new ListItem("First point"));
    list.add(new ListItem("Second point"));
    list.add(new ListItem("Third point"));
    subCatPart.add(list);
  }

  private static void addEmptyLine(Paragraph paragraph, int number) {
    for (int i = 0; i < number; i++) {
      paragraph.add(new Paragraph(" "));
    }
  }

}
