package utils;

import java.net.URI;

import javax.tools.SimpleJavaFileObject;

public class CompilableSource extends SimpleJavaFileObject {

	final String code;

    public CompilableSource( String className, String code) {
        super( URI.create("string:///" + className.replace('.','/') + Kind.SOURCE.extension),Kind.SOURCE);
        this.code = code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }

}
