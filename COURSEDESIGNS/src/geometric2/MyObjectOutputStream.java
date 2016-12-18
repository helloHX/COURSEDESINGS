package geometric2;
import java.io.*;
/*为避免重复打开文件对文件的追加造成多个header
 * 为对测试时对文件的读取避免IOExption
 * 重写ObjectOutputstream*/
public class MyObjectOutputStream extends ObjectOutputStream{
	
	public MyObjectOutputStream() throws IOException {  
		         super(); 
		  }
	
	public MyObjectOutputStream(OutputStream out) throws IOException {
		   super(out);
		   } 
		  
		  @Override
	protected void writeStreamHeader() throws IOException { 
		     return;
		  }

}
