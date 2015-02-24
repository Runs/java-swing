import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FormDemo extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response){		
		try{
			response.setContentType("text/html;charset=gb2312"); //����ͷ��
			PrintWriter out=response.getWriter();  //�õ�PrintWriterʵ��
			String name,age,sex,phone,address,email;  //��������
			name=request.getParameter("Name");  //�õ�����
			sex=request.getParameter("Sex");
			phone=request.getParameter("Phone");
			address=request.getParameter("Address");
			email=request.getParameter("Email");
			out.println("<HTML><HEAD><TITLE>a</TITLE></HEAD>");  //�����Ϣ���ͻ���
			out.println("<BODY>");
			out.println("<P><H3>���֣�"+convertToChinese(name)+"</H3></P>");
			out.println("<P><H3>�Ա�"+convertToChinese(sex)+"</H3></P>");
			out.println("<P><H3>�绰��"+phone+"</H3></P>");
			out.println("<P><H3>��ַ��"+convertToChinese(address)+"</H3></P>");
			out.println("<P><H3>�����ʼ���"+email+"</H3></P>");
			out.println("</BODY></HTML>");			
		
		}
		catch (Exception ex){
			ex.printStackTrace();  //���������Ϣ
		}
	}
	
	private String convertToChinese(String source){
		String s="";
		try{
			 s=new String(source.getBytes("ISO8859_1"));  //�ַ�����ת��
		}
		catch(java.io.UnsupportedEncodingException ex){
			ex.printStackTrace();  //���������Ϣ
		}
		return s; //����ת������ַ���
	}	
}