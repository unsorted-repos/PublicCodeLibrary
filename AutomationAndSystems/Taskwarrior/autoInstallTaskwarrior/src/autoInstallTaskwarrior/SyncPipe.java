package autoInstallTaskwarrior;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

class SyncPipe implements Runnable
{
	public SyncPipe(InputStream istrm, OutputStream ostrm) {
		istrm_ = istrm;
		ostrm_ = ostrm;
	}
	public void run() {
		  
	  try
	  {
	      final byte[] buffer = new byte[1024];
	      for (int length = 0; (length = istrm_.read(buffer)) != -1; )
	      {
	          ostrm_.write(buffer, 0, length);
	          System.out.println("syncpipe output"+ostrm_.toString());
	              
	          }
	      }
	      catch (Exception e)
	      {
	          e.printStackTrace();
	      }
	  }
	  private final OutputStream ostrm_;
	  private final InputStream istrm_;
	  
	  public void readOutput(InputStream istrm_) {
		  
	  }
	  
	  public void readOutput(OutputStream ostrm_) {
		  
	  }
}