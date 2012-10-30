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
           // URLの部分の?以降からname=""となっている部分を取り出す．
          // 例：http://localhost:8080/TomcatTest/servlettest1?name=aiueo
          // の場合，次の行でaiueoが出力される．
          // 英数字ならそのままでOKだが，2バイト文字はそのままだと文字化けするので，処理が必要かな．

          Display d = new Display(w);
          d.header("トップページ");

          w.println("<div class=\"container\">");

          w.println("<div class=\"hero-unit\">");
          w.println("<h1>INVIGORATOR</h1>");
          w.println("<p>Invigorate our body using the health management refrigerator!</p>");
          w.println("</div>");

          w.println("<div class=\"well\" style=\"max-width: 400px; margin: 0 auto 10px;\">");
          w.println("<a class=\"btn btn-large btn-block\" type=\"button\" href=\"recommendation\">レシピ提案</a>");
          w.println("<a class=\"btn btn-large btn-block\" type=\"button\" href=\"inventory\">冷蔵庫の中身を見る</a>");
          w.println("<a class=\"btn btn-large btn-block\" type=\"button\" href=\"addition\">食品を追加する</a>");
          w.println("</div>");

          /* パラメータkeyによる分岐 */
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
//        	  w.println("食品成分表の取得<br />");
//        	  fddfd(w, Integer.parseInt(from), Integer.parseInt(to));
//          }
     }

          w.println("</div>");

          w.println("</body>");
          w.println("</html>");


     }


     /* Amazonで検索 */
     /**
      *
      * @param out, barcode
      * @throws IOException
      *
      * バーコードを検索ワードにして、Amazonで検索する。
      * 該当する商品があれば、商品名とカテゴリ名を表示。
      * 該当する商品がなければ、「結果なし」とする。
      */
     protected void searchAmazon(PrintWriter out, String barcode) throws IOException{

    	 //リクエストURL作成
    	 String url_result = "http://www.amazon.co.jp/s/ref=nb_sb_noss?field-keywords=" + barcode;

         Document doc_result = Jsoup.connect(url_result).get();

         Elements elements = doc_result.select("div#result_0");

         // 次のコードで詳細ページのURLが取得できる。何故かは知らない。
         // こうなった経緯を以下のコメントに示す。
         String url_detail = elements.first().select("a").first().attr("href");

/*
// 意味のあるコメントアウト
// ここから
         // 各要素をぶん回します
         // ※elementsの要素数は1つ（<div class="result_0">だけ）である。
         for (Element element : elements) {
        	 // で、さらに要素を見ていく。（つまり、<div class="result_0">の子を見ていく。）
        	 // Amazonの検索結果ページのHTMLソースではdiv,h3,ul*2ということで子の数は4のはずだが、
        	 // size()で要素数を取得すると、なぜか'3'となり、一致していないという謎仕様・・・わけがわからないよ
        	 // しかしながら、商品詳細ページへのURLは含まれているので、強引に取得することにしました。
        	 for(int j=0; j<3; j++){
        	 out.println(j + ": " + element.child(j).tagName()  + ", " + element.child(j).html()+ "<br />");
        	 out.println("<bar />");
        	 }
         }
// ここまで
*/

         out.println("url:" + url_detail + "<br />");

         // 詳細ページから情報を抜き出し
         Document doc_detail = Jsoup.connect(url_detail).get();
      // titleタグを取得
      			Elements span = doc_detail.getElementsByTag("title");
      			// こちらでもtitleを取得できる
      			// String title = document.title();
//cpAsinTitle
      	      out.println("商品名" + span.get(0).text() + "<br />");

//zg_hrsr_ladder
      	  //  Elements cate = doc_detail.getElementsByClass("zg_hrsr_ladder");
//  	      out.println("カテゴリ：" + cate.get(0).text() + "<br />");


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
//         out.println("レスポンスヘッダ:");
//         while (it.hasNext()){
//             String key= (String)it.next();
//             out.println("  " + key + ": " + headers.get(key));
//         }
//
//         out.println("レスポンスコード[" + urlconn.getResponseCode() + "] " +
//                            "レスポンスメッセージ[" + urlconn.getResponseMessage() + "]");
//         out.println("\n---- ボディ ----");
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


     /* COOKPADで検索 */



public void fddfd(PrintWriter out, int from, int to) throws IOException{
    	 // 食品成分表登録ルーチン

    out.println("<table class=\"table table-hover table-condensed\">");
    out.println("<thead>");
    out.println("<tr>");
    out.println("<th>コード</th>");
    out.println("<th>名前</th>");
    out.println("</tr>");
    out.println("</thead>");
    out.println("<tbody>");


    //ArrayList型のオブジェクトのインスタンスを格納する箱を用意
    ArrayList<Data> data = new ArrayList<Data>();

    	 for(int i =from; i<=to; i++){
    	 String url  = "http://fooddb.jp/details/details.pl?ITEM_NO=1_" + i +"_6";

         Document document = Jsoup.connect(url).get();
       	out.println("<tr>");
        out.println("<td>" + i + "</td>");
//         if(document!=null){
if(document.getElementsByTag("title").first().text().equals("[ERROR]食品詳細表示")){
        out.println("<td>そんな番号ない</td>");

}
else{

         Elements span = document.getElementsByClass("food_name");





      	if(span==null || span.isEmpty()){
      		out.println("<td>null</td>");
       }else{
          out.println("<td>" + span.get(0).text() + "</td>");
          data.add(new Data(i,span.get(0).text()));
       }




//         out.println("データ:" + span.get(0).text() + "<br />");

    	 }

         out.println("</tr>");

    	 }
         out.println("</tbody>");
         out.println("</table>");
         (new AddFood(out, data)).execute();
}




}