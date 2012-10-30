package test;

import java.io.PrintWriter;

public class Display {

	PrintWriter out;

	Display(PrintWriter w){
		out = w;
	}

	public void header(String title){

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\" />");
        out.println("<meta name=\"viewport\" content=\"width=device-width, user-scalable=yes, initial-scale=1.0, maxmum-scale=3.0\" />");
        out.println("<title>" + title + "</title>");
        out.println("<link href=\"bootstrap/css/bootstrap.css\" rel=\"stylesheet\">");
        out.println("<link href=\"bootstrap/css/bootstrap-responsive.css\" rel=\"stylesheet\">");
        out.println("<style>");
        out.println("body {");
        out.println("padding-top: 60px;");
        out.println("}");
        out.println("</style>");

        /* JavaScript */
        out.println("<script type=\"text/jacascript\" src=\"jquery/jquery-1.8.2.min.js\">");
        out.println("<script>");
        out.println("var name;");
        out.println("var price;");
        out.println("var number;");
        out.println("var data;");
        out.println("jQuery(function(){");
        out.println("    jQuery('.add_top').click(function(){");
        out.println("        getData();");
        out.println("        if (jQuery('tbody tr').length) {");
        out.println("            jQuery('tbody tr:first').before(data);");
        out.println("        } else {");
        out.println("            jQuery('tbody').append(data);");
        out.println("        }");
        out.println("        jQuery('.slide').slideDown(\"fast\");");
        out.println("    });");
        out.println("");
        out.println("    jQuery('.add_bottom').click(function(){");
        out.println("        getData();");
        out.println("        jQuery('tbody').append(data);");
        out.println("        jQuery('.slide').slideDown(\"fast\");");
        out.println("    });");
        out.println("");
        out.println("    jQuery('.del_top').click(function(){");
        out.println("        if (jQuery('tbody tr').length) {");
        out.println("            jQuery('tbody tr:first').remove();");
        out.println("        }");
        out.println("    });");

        out.println("jQuery('.del_bottom').click(function(){");
        out.println("        if (jQuery('tbody tr').length) {");
        out.println("            jQuery('tbody tr:last').remove();");
        out.println("        }");
        out.println("    });");

        out.println("    jQuery('.clear').click(function(){");
        out.println("        if (jQuery('tbody tr').length) {");
        out.println("            jQuery('tbody tr').remove();");
        out.println("        }");
        out.println("    });");
        out.println("});");
        out.println("function getData() {");
        out.println("    name = jQuery('#name').val();");
        out.println("    price = jQuery('#price').val();");
        out.println("    number = jQuery('#number').val();");
        out.println("    data = '<tr><td><div class=\"slide\">' + name + '</div></td><td><div class=\"slide price\">' + price+ '</div></td><td><div class=\"slide number\">' + number + '</div></td></tr>';");
        out.println("}");
        out.println("</script>");

        out.println("</head>");
        out.println("<body>");

        out.println("<div class=\"navbar navbar-inverse navbar-fixed-top\">");
        out.println("<div class=\"navbar-inner\">");
        out.println("<div class=\"container\">");
        out.println("<a class=\"btn btn-navbar\" data-toggle=\"collapse\" data-target=\".nav-collapse\">");
        out.println("  <span class=\"icon-bar\"></span>");
        out.println("  <span class=\"icon-bar\"></span>");
        out.println("  <span class=\"icon-bar\"></span>");
        out.println("</a>");
        out.println("<a class=\"brand\" href=\"home\">INVIGORATOR</a>");
        out.println("<div class=\"nav-collapse collapse\">");
        out.println("<ul class=\"nav\">");
        out.println("  <li class=\"active\"><a href=\"home\">Home</a></li>");
        out.println("  <li><a href=\"recommendation\">Recipe</a></li>");
        out.println("  <li><a href=\"inventory\">View</a></li>");
        out.println("  <li><a href=\"addition\">Addition</a></li>");
        out.println("</ul>");
        out.println("</div><!--/.nav-collapse -->");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("<script src=\"bootstrap/js/bootstrap.min.js\"></script>");
	}
}
