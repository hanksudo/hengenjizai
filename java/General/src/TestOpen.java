import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
public class TestOpen
{
    public static void main( String[] args ) {
        if( !Desktop.isDesktopSupported() ) {
            System.out.println( "Desktop class is not supported on the current platform." );
            System.exit( 0 );
        }
        
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse( new URI( "http://www.google.com.tw/" ) );
//            desktop.mail( new URI( "mailto:id@gmail.com" ) );
            desktop.open( new File( "foo.xls" ) );
            desktop.edit( new File( "foo.doc" ) );
            desktop.print( new File( "foo.txt" ) );
        }
        catch( URISyntaxException ex ) { ex.printStackTrace(); }
        catch( IOException ex ) { ex.printStackTrace(); }
    }
}
