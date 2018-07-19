package compilation;

import java.net.URI;

import javax.tools.SimpleJavaFileObject;

public class CompilableJavaObject extends SimpleJavaFileObject {

	final String code;

    public CompilableJavaObject( String className, String code) {
        super( URI.create("string:///" + className.replace('.','/') + Kind.SOURCE.extension),Kind.SOURCE);
        this.code = code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }

}
