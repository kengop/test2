package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@SuppressWarnings("serial")
public class ServletTest1 extends HttpServlet {

     protected void doGet(
          HttpServletRequest req,
          HttpServletResponse resp)
               throws ServletException, IOException {

          resp.setContentType("text/html; charset=Shift_JIS");
          PrintWriter w = resp.getWriter();
           // URL�̕�����?�ȍ~����name=""�ƂȂ��Ă��镔�������o���D
          // ��Fhttp://localhost:8080/TomcatTest/servlettest1?name=aiueo
          // �̏ꍇ�C���̍s��aiueo���o�͂����D
          // �p�����Ȃ炻�̂܂܂�OK�����C2�o�C�g�����͂��̂܂܂��ƕ�����������̂ŁC�������K�v���ȁD

          Display d = new Display(w);
          d.header("�g�b�v�y�[�W");

          w.println("<div class=\"container\">");

          w.println("<div class=\"hero-unit\">");
          w.println("<h1>INVIGORATOR</h1>");
          w.println("<p>Invigorate our body using the health management refrigerator!</p>");
          w.println("</div>");

          w.println("<div class=\"well\" style=\"max-width: 400px; margin: 0 auto 10px;\">");
          w.println("<a class=\"btn btn-large btn-block\" type=\"button\" href=\"recommendation\">���V�s���</a>");
          w.println("<a class=\"btn btn-large btn-block\" type=\"button\" href=\"inventory\">�①�ɂ̒��g������</a>");
          w.println("<a class=\"btn btn-large btn-block\" type=\"button\" href=\"addition\">�H�i��ǉ�����</a>");
          w.println("</div>");

          /* �p�����[�^key�ɂ�镪�� */
          String val = req.getParameter("key");

          if(val == null || val.length() == 0){

          }
          else{
              w.println("key:=" + val);

        	  if(val.equals("1")){
            w.println("one");
          }
          else if(val.equals("amazon")){
            w.println("amazon <br />");
            String keyword = req.getParameter("word");
            searchAmazon(w, keyword);

          }
//          else if(val.equals("view")){
//        	  w.println("view <br />");
//              (new View(w),).execute();
//          }
//          else if(val.equals("add")){
//        	  String keyword = req.getParameter("word");
//        	  w.println("view <br />");
//              (new Addition(w, keyword)).execute();
//          }
//          else if(val.equals("dbdb")){
//        	  String from = req.getParameter("from");
//        	  String to = req.getParameter("to");
//        	  w.println("�H�i�����\�̎擾<br />");
//        	  fddfd(w, Integer.parseInt(from), Integer.parseInt(to));
//          }
     }

          w.println("</div>");

          w.println("</body>");
          w.println("</html>");


     }


     /* Amazon�Ō��� */
     /**
      *
      * @param out, barcode
      * @throws IOException
      *
      * �o�[�R�[�h���������[�h�ɂ��āAAmazon�Ō�������B
      * �Y�����鏤�i������΁A���i���ƃJ�e�S������\���B
      * �Y�����鏤�i���Ȃ���΁A�u���ʂȂ��v�Ƃ���B
      */
     protected void searchAmazon(PrintWriter out, String barcode) throws IOException{

    	 //���N�G�X�gURL�쐬
    	 String url_result = "http://www.amazon.co.jp/s/ref=nb_sb_noss?field-keywords=" + barcode;

         Document doc_result = Jsoup.connect(url_result).get();

         Elements elements = doc_result.select("div#result_0");

         // ���̃R�[�h�ŏڍ׃y�[�W��URL���擾�ł���B���̂��͒m��Ȃ��B
         // �����Ȃ����o�܂��ȉ��̃R�����g�Ɏ����B
         String url_detail = elements.first().select("a").first().attr("href");

/*
// �Ӗ��̂���R�����g�A�E�g
// ��������
         // �e�v�f���Ԃ�񂵂܂�
         // ��elements�̗v�f����1�i<div class="result_0">�����j�ł���B
         for (Element element : elements) {
        	 // �ŁA����ɗv�f�����Ă����B�i�܂�A<div class="result_0">�̎q�����Ă����B�j
        	 // Amazon�̌������ʃy�[�W��HTML�\�[�X�ł�div,h3,ul*2�Ƃ������ƂŎq�̐���4�̂͂������A
        	 // size()�ŗv�f�����擾����ƁA�Ȃ���'3'�ƂȂ�A��v���Ă��Ȃ��Ƃ�����d�l�E�E�E�킯���킩��Ȃ���
        	 // �������Ȃ���A���i�ڍ׃y�[�W�ւ�URL�͊܂܂�Ă���̂ŁA�����Ɏ擾���邱�Ƃɂ��܂����B
        	 for(int j=0; j<3; j++){
        	 out.println(j + ": " + element.child(j).tagName()  + ", " + element.child(j).html()+ "<br />");
        	 out.println("<bar />");
        	 }
         }
// �����܂�
*/

         out.println("url:" + url_detail + "<br />");

         // �ڍ׃y�[�W������𔲂��o��
         Document doc_detail = Jsoup.connect(url_detail).get();
      // title�^�O���擾
      			Elements span = doc_detail.getElementsByTag("title");
      			// ������ł�title���擾�ł���
      			// String title = document.title();
//cpAsinTitle
      	      out.println("���i��" + span.get(0).text() + "<br />");

//zg_hrsr_ladder
      	  //  Elements cate = doc_detail.getElementsByClass("zg_hrsr_ladder");
//  	      out.println("�J�e�S���F" + cate.get(0).text() + "<br />");


    	 }


//      			TextView tv = (TextView) findViewById(R.id.textView1);
//      			tv.setText(title.toString() + "\n" + body.toString());

//         HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();
//         urlconn.setRequestMethod("GET");
//         urlconn.setInstanceFollowRedirects(false);
//         urlconn.setRequestProperty("Accept-Language", "ja;q=0.7,en;q=0.3");
//
//         urlconn.connect();
//
//         Map headers = urlconn.getHeaderFields();
//         Iterator it = headers.keySet().iterator();
//         out.println("<pre>");
//         out.println("���X�|���X�w�b�_:");
//         while (it.hasNext()){
//             String key= (String)it.next();
//             out.println("  " + key + ": " + headers.get(key));
//         }
//
//         out.println("���X�|���X�R�[�h[" + urlconn.getResponseCode() + "] " +
//                            "���X�|���X���b�Z�[�W[" + urlconn.getResponseMessage() + "]");
//         out.println("\n---- �{�f�B ----");
//
//         BufferedReader reader =
//             new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
//
//         while (true){
//             String line = reader.readLine();
//             if ( line == null ){
//                 break;
//             }
//             out.println(line);
//         }
//
//         reader.close();
//         urlconn.disconnect();
//         out.println("</pre>");


     /* COOKPAD�Ō��� */



public void fddfd(PrintWriter out, int from, int to) throws IOException{
    	 // �H�i�����\�o�^���[�`��

    out.println("<table class=\"table table-hover table-condensed\">");
    out.println("<thead>");
    out.println("<tr>");
    out.println("<th>�R�[�h</th>");
    out.println("<th>���O</th>");
    out.println("</tr>");
    out.println("</thead>");
    out.println("<tbody>");


    //ArrayList�^�̃I�u�W�F�N�g�̃C���X�^���X���i�[���锠��p��
    ArrayList<Data> data = new ArrayList<Data>();

    	 for(int i =from; i<=to; i++){
    	 String url  = "http://fooddb.jp/details/details.pl?ITEM_NO=1_" + i +"_6";

         Document document = Jsoup.connect(url).get();
       	out.println("<tr>");
        out.println("<td>" + i + "</td>");
//         if(document!=null){
if(document.getElementsByTag("title").first().text().equals("[ERROR]�H�i�ڍו\��")){
        out.println("<td>����Ȕԍ��Ȃ�</td>");

}
else{

         Elements span = document.getElementsByClass("food_name");





      	if(span==null || span.isEmpty()){
      		out.println("<td>null</td>");
       }else{
          out.println("<td>" + span.get(0).text() + "</td>");
          data.add(new Data(i,span.get(0).text()));
       }




//         out.println("�f�[�^:" + span.get(0).text() + "<br />");

    	 }

         out.println("</tr>");

    	 }
         out.println("</tbody>");
         out.println("</table>");
         (new AddFood(out, data)).execute();
}




}