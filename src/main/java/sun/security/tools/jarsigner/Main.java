package sun.security.tools.jarsigner;

import com.sun.jarsigner.ContentSigner;
import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AlgorithmParameters;
import java.security.CodeSigner;
import java.security.CryptoPrimitive;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.Timestamp;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertSelector;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.text.Collator;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TimeZone;
import java.util.Vector;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import jdk.internal.dynalink.CallSiteDescriptor;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import sun.rmi.rmic.iiop.Constants;
import sun.security.pkcs.PKCS7;
import sun.security.pkcs.SignerInfo;
import sun.security.timestamp.TimestampToken;
import sun.security.tools.KeyStoreUtil;
import sun.security.tools.PathList;
import sun.security.tools.jarsigner.SignatureFile;
import sun.security.util.DerInputStream;
import sun.security.util.DerValue;
import sun.security.util.DisabledAlgorithmConstraints;
import sun.security.util.KeyUtil;
import sun.security.util.ManifestDigester;
import sun.security.util.Password;
import sun.security.util.SignatureFileVerifier;
import sun.security.validator.Validator;
import sun.security.validator.ValidatorException;
import sun.security.x509.AlgorithmId;
import sun.security.x509.NetscapeCertTypeExtension;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:sun/security/tools/jarsigner/Main.class */
public class Main {
    private static final ResourceBundle rb = ResourceBundle.getBundle("sun.security.tools.jarsigner.Resources");
    private static final Collator collator = Collator.getInstance();
    private static final String META_INF = "META-INF/";
    private static final Class<?>[] PARAM_STRING;
    private static final String NONE = "NONE";
    private static final String P11KEYSTORE = "PKCS11";
    private static final long SIX_MONTHS = 15552000000L;
    private static final long ONE_YEAR = 31622400000L;
    private static final DisabledAlgorithmConstraints DISABLED_CHECK;
    private static final DisabledAlgorithmConstraints LEGACY_CHECK;
    private static final Set<CryptoPrimitive> DIGEST_PRIMITIVE_SET;
    private static final Set<CryptoPrimitive> SIG_PRIMITIVE_SET;
    static final String VERSION = "1.0";
    static final int IN_KEYSTORE = 1;
    static final int IN_SCOPE = 2;
    static final int NOT_ALIAS = 4;
    static final int SIGNED_BY_ALIAS = 8;
    X509Certificate[] certChain;
    PrivateKey privateKey;
    KeyStore store;
    String keystore;
    String jarfile;
    String alias;
    char[] storepass;
    boolean protectedPath;
    String storetype;
    String providerName;
    char[] keypass;
    String sigfile;
    String sigalg;
    String signedjar;
    String tsaUrl;
    String tsaAlias;
    String altCertChain;
    String tSAPolicyID;
    PKIXBuilderParameters pkixParameters;
    private static MessageFormat validityTimeForm;
    private static MessageFormat notYetTimeForm;
    private static MessageFormat expiredTimeForm;
    private static MessageFormat expiringTimeForm;
    private static MessageFormat signTimeForm;
    boolean nullStream = false;
    boolean token = false;
    List<String> ckaliases = new ArrayList();
    Vector<String> providers = null;
    HashMap<String, String> providerArgs = new HashMap<>();
    String digestalg = "SHA-256";
    String tSADigestAlg = "SHA-256";
    boolean verify = false;
    String verbose = null;
    boolean showcerts = false;
    boolean debug = false;
    boolean signManifest = true;
    boolean externalSF = true;
    boolean strict = false;
    private ByteArrayOutputStream baos = new ByteArrayOutputStream(2048);
    private byte[] buffer = new byte[8192];
    private ContentSigner signingMechanism = null;
    private String altSignerClass = null;
    private String altSignerClasspath = null;
    private ZipFile zipFile = null;
    private boolean hasExpiringCert = false;
    private boolean hasExpiringTsaCert = false;
    private boolean noTimestamp = true;
    private Date expireDate = null;
    private Date tsaExpireDate = null;
    boolean hasTimestampBlock = false;
    private PublicKey weakPublicKey = null;
    private boolean disabledAlgFound = false;
    private String legacyDigestAlg = null;
    private String legacyTsaDigestAlg = null;
    private String legacySigAlg = null;
    private int legacyAlg = 0;
    private int disabledAlg = 0;
    private boolean hasExpiredCert = false;
    private boolean hasExpiredTsaCert = false;
    private boolean notYetValidCert = false;
    private boolean chainNotValidated = false;
    private boolean tsaChainNotValidated = false;
    private boolean notSignedByAlias = false;
    private boolean aliasNotInStore = false;
    private boolean hasUnsignedEntry = false;
    private boolean badKeyUsage = false;
    private boolean badExtendedKeyUsage = false;
    private boolean badNetscapeCertType = false;
    private boolean signerSelfSigned = false;
    private Throwable chainNotValidatedReason = null;
    private Throwable tsaChainNotValidatedReason = null;
    Set<X509Certificate> trustedCerts = new HashSet();
    private Map<CodeSigner, Integer> cacheForInKS = new IdentityHashMap();
    Hashtable<Certificate, String> storeHash = new Hashtable<>();
    Map<CodeSigner, String> cacheForSignerInfo = new IdentityHashMap();

    static {
        collator.setStrength(0);
        PARAM_STRING = new Class[]{String.class};
        DISABLED_CHECK = new DisabledAlgorithmConstraints("jdk.jar.disabledAlgorithms");
        LEGACY_CHECK = new DisabledAlgorithmConstraints("jdk.security.legacyAlgorithms");
        DIGEST_PRIMITIVE_SET = Collections.unmodifiableSet(EnumSet.of(CryptoPrimitive.MESSAGE_DIGEST));
        SIG_PRIMITIVE_SET = Collections.unmodifiableSet(EnumSet.of(CryptoPrimitive.SIGNATURE));
        validityTimeForm = null;
        notYetTimeForm = null;
        expiredTimeForm = null;
        expiringTimeForm = null;
        signTimeForm = null;
    }

    public static void main(String[] strArr) throws Exception {
        new Main().run(strArr);
    }

    public void run(String[] strArr) {
        Class<?> cls;
        Object newInstance;
        try {
            try {
                parseArgs(strArr);
                if (this.providers != null) {
                    ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
                    Enumeration<String> elements = this.providers.elements();
                    while (elements.hasMoreElements()) {
                        String nextElement = elements.nextElement();
                        if (systemClassLoader != null) {
                            cls = systemClassLoader.loadClass(nextElement);
                        } else {
                            cls = Class.forName(nextElement);
                        }
                        String str = this.providerArgs.get(nextElement);
                        if (str == null) {
                            newInstance = cls.newInstance();
                        } else {
                            newInstance = cls.getConstructor(PARAM_STRING).newInstance(str);
                        }
                        if (!(newInstance instanceof Provider)) {
                            throw new Exception(new MessageFormat(rb.getString("provName.not.a.provider")).format(new Object[]{nextElement}));
                        }
                        Security.addProvider((Provider) newInstance);
                    }
                }
                if (this.verify) {
                    try {
                        loadKeyStore(this.keystore, false);
                    } catch (Exception e) {
                        if (this.keystore != null || this.storepass != null) {
                            System.out.println(rb.getString("jarsigner.error.") + e.getMessage());
                            System.exit(1);
                        }
                    }
                    verifyJar(this.jarfile);
                } else {
                    loadKeyStore(this.keystore, true);
                    getAliasInfo(this.alias);
                    if (this.altSignerClass != null) {
                        this.signingMechanism = loadSigningMechanism(this.altSignerClass, this.altSignerClasspath);
                    }
                    signJar(this.jarfile, this.alias, strArr);
                }
                if (this.keypass != null) {
                    Arrays.fill(this.keypass, ' ');
                    this.keypass = null;
                }
                if (this.storepass != null) {
                    Arrays.fill(this.storepass, ' ');
                    this.storepass = null;
                }
            } catch (Exception e2) {
                System.out.println(rb.getString("jarsigner.error.") + e2);
                if (this.debug) {
                    e2.printStackTrace();
                }
                System.exit(1);
                if (this.keypass != null) {
                    Arrays.fill(this.keypass, ' ');
                    this.keypass = null;
                }
                if (this.storepass != null) {
                    Arrays.fill(this.storepass, ' ');
                    this.storepass = null;
                }
            }
            if (this.strict) {
                int i = 0;
                if (this.disabledAlg != 0 || this.chainNotValidated || this.hasExpiredCert || this.hasExpiredTsaCert || this.notYetValidCert || this.signerSelfSigned) {
                    i = 0 | 4;
                }
                if (this.badKeyUsage || this.badExtendedKeyUsage || this.badNetscapeCertType) {
                    i |= 8;
                }
                if (this.hasUnsignedEntry) {
                    i |= 16;
                }
                if (this.notSignedByAlias || this.aliasNotInStore) {
                    i |= 32;
                }
                if (this.tsaChainNotValidated) {
                    i |= 64;
                }
                if (i != 0) {
                    System.exit(i);
                }
            }
        } catch (Throwable th) {
            if (this.keypass != null) {
                Arrays.fill(this.keypass, ' ');
                this.keypass = null;
            }
            if (this.storepass != null) {
                Arrays.fill(this.storepass, ' ');
                this.storepass = null;
            }
            throw th;
        }
    }

    void parseArgs(String[] strArr) {
        int indexOf;
        if (strArr.length == 0) {
            fullusage();
        }
        int i = 0;
        while (i < strArr.length) {
            String str = strArr[i];
            String str2 = null;
            if (str.startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR) && (indexOf = str.indexOf(58)) > 0) {
                str2 = str.substring(indexOf + 1);
                str = str.substring(0, indexOf);
            }
            if (!str.startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
                if (this.jarfile == null) {
                    this.jarfile = str;
                } else {
                    this.alias = str;
                    this.ckaliases.add(this.alias);
                }
            } else if (collator.compare(str, "-keystore") == 0) {
                i++;
                if (i == strArr.length) {
                    usageNoArg();
                }
                this.keystore = strArr[i];
            } else if (collator.compare(str, "-storepass") == 0) {
                i++;
                if (i == strArr.length) {
                    usageNoArg();
                }
                this.storepass = getPass(str2, strArr[i]);
            } else if (collator.compare(str, "-storetype") == 0) {
                i++;
                if (i == strArr.length) {
                    usageNoArg();
                }
                this.storetype = strArr[i];
            } else if (collator.compare(str, "-providerName") == 0) {
                i++;
                if (i == strArr.length) {
                    usageNoArg();
                }
                this.providerName = strArr[i];
            } else if (collator.compare(str, "-provider") == 0 || collator.compare(str, "-providerClass") == 0) {
                i++;
                if (i == strArr.length) {
                    usageNoArg();
                }
                if (this.providers == null) {
                    this.providers = new Vector<>(3);
                }
                this.providers.add(strArr[i]);
                if (strArr.length > i + 1 && collator.compare(strArr[i + 1], "-providerArg") == 0) {
                    if (strArr.length == i + 2) {
                        usageNoArg();
                    }
                    this.providerArgs.put(strArr[i], strArr[i + 2]);
                    i += 2;
                }
            } else if (collator.compare(str, "-protected") == 0) {
                this.protectedPath = true;
            } else if (collator.compare(str, "-certchain") == 0) {
                i++;
                if (i == strArr.length) {
                    usageNoArg();
                }
                this.altCertChain = strArr[i];
            } else if (collator.compare(str, "-tsapolicyid") == 0) {
                i++;
                if (i == strArr.length) {
                    usageNoArg();
                }
                this.tSAPolicyID = strArr[i];
            } else if (collator.compare(str, "-tsadigestalg") == 0) {
                i++;
                if (i == strArr.length) {
                    usageNoArg();
                }
                this.tSADigestAlg = strArr[i];
            } else if (collator.compare(str, "-debug") == 0) {
                this.debug = true;
            } else if (collator.compare(str, "-keypass") == 0) {
                i++;
                if (i == strArr.length) {
                    usageNoArg();
                }
                this.keypass = getPass(str2, strArr[i]);
            } else if (collator.compare(str, "-sigfile") == 0) {
                i++;
                if (i == strArr.length) {
                    usageNoArg();
                }
                this.sigfile = strArr[i];
            } else if (collator.compare(str, "-signedjar") == 0) {
                i++;
                if (i == strArr.length) {
                    usageNoArg();
                }
                this.signedjar = strArr[i];
            } else if (collator.compare(str, "-tsa") == 0) {
                i++;
                if (i == strArr.length) {
                    usageNoArg();
                }
                this.tsaUrl = strArr[i];
            } else if (collator.compare(str, "-tsacert") == 0) {
                i++;
                if (i == strArr.length) {
                    usageNoArg();
                }
                this.tsaAlias = strArr[i];
            } else if (collator.compare(str, "-altsigner") == 0) {
                i++;
                if (i == strArr.length) {
                    usageNoArg();
                }
                this.altSignerClass = strArr[i];
            } else if (collator.compare(str, "-altsignerpath") == 0) {
                i++;
                if (i == strArr.length) {
                    usageNoArg();
                }
                this.altSignerClasspath = strArr[i];
            } else if (collator.compare(str, "-sectionsonly") == 0) {
                this.signManifest = false;
            } else if (collator.compare(str, "-internalsf") == 0) {
                this.externalSF = false;
            } else if (collator.compare(str, "-verify") == 0) {
                this.verify = true;
            } else if (collator.compare(str, "-verbose") == 0) {
                this.verbose = str2 != null ? str2 : "all";
            } else if (collator.compare(str, "-sigalg") == 0) {
                i++;
                if (i == strArr.length) {
                    usageNoArg();
                }
                this.sigalg = strArr[i];
            } else if (collator.compare(str, "-digestalg") == 0) {
                i++;
                if (i == strArr.length) {
                    usageNoArg();
                }
                this.digestalg = strArr[i];
            } else if (collator.compare(str, "-certs") == 0) {
                this.showcerts = true;
            } else if (collator.compare(str, "-strict") == 0) {
                this.strict = true;
            } else if (collator.compare(str, "-h") == 0 || collator.compare(str, "-help") == 0) {
                fullusage();
            } else {
                System.err.println(rb.getString("Illegal.option.") + str);
                usage();
            }
            i++;
        }
        if (this.verbose == null) {
            this.showcerts = false;
        }
        if (this.jarfile == null) {
            System.err.println(rb.getString("Please.specify.jarfile.name"));
            usage();
        }
        if (!this.verify && this.alias == null) {
            System.err.println(rb.getString("Please.specify.alias.name"));
            usage();
        }
        if (!this.verify && this.ckaliases.size() > 1) {
            System.err.println(rb.getString("Only.one.alias.can.be.specified"));
            usage();
        }
        if (this.storetype == null) {
            this.storetype = KeyStore.getDefaultType();
        }
        this.storetype = KeyStoreUtil.niceStoreTypeName(this.storetype);
        try {
            if (this.signedjar != null && new File(this.signedjar).getCanonicalPath().equals(new File(this.jarfile).getCanonicalPath())) {
                this.signedjar = null;
            }
        } catch (IOException e) {
        }
        if (P11KEYSTORE.equalsIgnoreCase(this.storetype) || KeyStoreUtil.isWindowsKeyStore(this.storetype)) {
            this.token = true;
            if (this.keystore == null) {
                this.keystore = NONE;
            }
        }
        if (NONE.equals(this.keystore)) {
            this.nullStream = true;
        }
        if (this.token && !this.nullStream) {
            System.err.println(MessageFormat.format(rb.getString(".keystore.must.be.NONE.if.storetype.is.{0}"), this.storetype));
            usage();
        }
        if (this.token && this.keypass != null) {
            System.err.println(MessageFormat.format(rb.getString(".keypass.can.not.be.specified.if.storetype.is.{0}"), this.storetype));
            usage();
        }
        if (this.protectedPath && (this.storepass != null || this.keypass != null)) {
            System.err.println(rb.getString("If.protected.is.specified.then.storepass.and.keypass.must.not.be.specified"));
            usage();
        }
        if (KeyStoreUtil.isWindowsKeyStore(this.storetype)) {
            if (this.storepass != null || this.keypass != null) {
                System.err.println(rb.getString("If.keystore.is.not.password.protected.then.storepass.and.keypass.must.not.be.specified"));
                usage();
            }
        }
    }

    static char[] getPass(String str, String str2) {
        char[] passWithModifier = KeyStoreUtil.getPassWithModifier(str, str2, rb, collator);
        if (passWithModifier != null) {
            return passWithModifier;
        }
        usage();
        return null;
    }

    static void usageNoArg() {
        System.out.println(rb.getString("Option.lacks.argument"));
        usage();
    }

    static void usage() {
        System.out.println();
        System.out.println(rb.getString("Please.type.jarsigner.help.for.usage"));
        System.exit(1);
    }

    static void fullusage() {
        System.out.println(rb.getString("Usage.jarsigner.options.jar.file.alias"));
        System.out.println(rb.getString(".jarsigner.verify.options.jar.file.alias."));
        System.out.println();
        System.out.println(rb.getString(".keystore.url.keystore.location"));
        System.out.println();
        System.out.println(rb.getString(".storepass.password.password.for.keystore.integrity"));
        System.out.println();
        System.out.println(rb.getString(".storetype.type.keystore.type"));
        System.out.println();
        System.out.println(rb.getString(".keypass.password.password.for.private.key.if.different."));
        System.out.println();
        System.out.println(rb.getString(".certchain.file.name.of.alternative.certchain.file"));
        System.out.println();
        System.out.println(rb.getString(".sigfile.file.name.of.SF.DSA.file"));
        System.out.println();
        System.out.println(rb.getString(".signedjar.file.name.of.signed.JAR.file"));
        System.out.println();
        System.out.println(rb.getString(".digestalg.algorithm.name.of.digest.algorithm"));
        System.out.println();
        System.out.println(rb.getString(".sigalg.algorithm.name.of.signature.algorithm"));
        System.out.println();
        System.out.println(rb.getString(".verify.verify.a.signed.JAR.file"));
        System.out.println();
        System.out.println(rb.getString(".verbose.suboptions.verbose.output.when.signing.verifying."));
        System.out.println(rb.getString(".suboptions.can.be.all.grouped.or.summary"));
        System.out.println();
        System.out.println(rb.getString(".certs.display.certificates.when.verbose.and.verifying"));
        System.out.println();
        System.out.println(rb.getString(".tsa.url.location.of.the.Timestamping.Authority"));
        System.out.println();
        System.out.println(rb.getString(".tsacert.alias.public.key.certificate.for.Timestamping.Authority"));
        System.out.println();
        System.out.println(rb.getString(".tsapolicyid.tsapolicyid.for.Timestamping.Authority"));
        System.out.println();
        System.out.println(rb.getString(".tsadigestalg.algorithm.of.digest.data.in.timestamping.request"));
        System.out.println();
        System.out.println(rb.getString(".altsigner.class.class.name.of.an.alternative.signing.mechanism"));
        System.out.println();
        System.out.println(rb.getString(".altsignerpath.pathlist.location.of.an.alternative.signing.mechanism"));
        System.out.println();
        System.out.println(rb.getString(".internalsf.include.the.SF.file.inside.the.signature.block"));
        System.out.println();
        System.out.println(rb.getString(".sectionsonly.don.t.compute.hash.of.entire.manifest"));
        System.out.println();
        System.out.println(rb.getString(".protected.keystore.has.protected.authentication.path"));
        System.out.println();
        System.out.println(rb.getString(".providerName.name.provider.name"));
        System.out.println();
        System.out.println(rb.getString(".providerClass.class.name.of.cryptographic.service.provider.s"));
        System.out.println(rb.getString(".providerArg.arg.master.class.file.and.constructor.argument"));
        System.out.println();
        System.out.println(rb.getString(".strict.treat.warnings.as.errors"));
        System.out.println();
        System.exit(0);
    }

    void verifyJar(String str) throws Exception {
        String format;
        boolean z = false;
        JarFile jarFile = null;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        HashMap hashMap4 = new HashMap();
        try {
            try {
                JarFile jarFile2 = new JarFile(str, true);
                Vector vector = new Vector();
                byte[] bArr = new byte[8192];
                Enumeration<JarEntry> entries = jarFile2.entries();
                while (entries.hasMoreElements()) {
                    JarEntry nextElement = entries.nextElement();
                    vector.addElement(nextElement);
                    InputStream inputStream = jarFile2.getInputStream(nextElement);
                    Throwable th = null;
                    try {
                        try {
                            String name = nextElement.getName();
                            if (signatureRelated(name) && SignatureFileVerifier.isBlockOrSF(name)) {
                                String substring = name.substring(name.lastIndexOf(47) + 1, name.lastIndexOf(46));
                                try {
                                    if (name.endsWith(".SF")) {
                                        boolean z2 = false;
                                        Iterator<Object> it = new Manifest(inputStream).getMainAttributes().keySet().iterator();
                                        while (true) {
                                            if (!it.hasNext()) {
                                                break;
                                            }
                                            String obj = it.next().toString();
                                            if (obj.endsWith("-Digest-Manifest")) {
                                                hashMap.put(substring, obj.substring(0, obj.length() - 16));
                                                z2 = true;
                                                break;
                                            }
                                        }
                                        if (!z2) {
                                            hashMap4.putIfAbsent(substring, String.format(rb.getString("history.unparsable"), name));
                                        }
                                    } else {
                                        hashMap3.put(substring, name);
                                        hashMap2.put(substring, new PKCS7(inputStream));
                                    }
                                } catch (IOException e) {
                                    hashMap4.putIfAbsent(substring, String.format(rb.getString("history.unparsable"), name));
                                }
                            } else {
                                do {
                                } while (inputStream.read(bArr, 0, bArr.length) != -1);
                            }
                            if (inputStream != null) {
                                if (0 != 0) {
                                    try {
                                        inputStream.close();
                                    } catch (Throwable th2) {
                                        th.addSuppressed(th2);
                                    }
                                } else {
                                    inputStream.close();
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            throw th3;
                        }
                    } catch (Throwable th4) {
                        if (inputStream != null) {
                            if (th != null) {
                                try {
                                    inputStream.close();
                                } catch (Throwable th5) {
                                    th.addSuppressed(th5);
                                }
                            } else {
                                inputStream.close();
                            }
                        }
                        throw th4;
                    }
                }
                Manifest manifest = jarFile2.getManifest();
                boolean z3 = false;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                if (manifest != null) {
                    if (this.verbose != null) {
                        System.out.println();
                    }
                    Enumeration elements = vector.elements();
                    String string = rb.getString("6SPACE");
                    while (elements.hasMoreElements()) {
                        JarEntry jarEntry = (JarEntry) elements.nextElement();
                        String name2 = jarEntry.getName();
                        z3 = z3 || SignatureFileVerifier.isBlockOrSF(name2);
                        CodeSigner[] codeSigners = jarEntry.getCodeSigners();
                        boolean z4 = codeSigners != null;
                        z |= z4;
                        this.hasUnsignedEntry |= (jarEntry.isDirectory() || z4 || signatureRelated(name2)) ? false : true;
                        int inKeyStore = inKeyStore(codeSigners);
                        boolean z5 = (inKeyStore & 1) != 0;
                        boolean z6 = (inKeyStore & 2) != 0;
                        this.notSignedByAlias |= (inKeyStore & 4) != 0;
                        if (this.keystore != null) {
                            this.aliasNotInStore |= (!z4 || z5 || z6) ? false : true;
                        }
                        StringBuffer stringBuffer = null;
                        if (this.verbose != null) {
                            stringBuffer = new StringBuffer();
                            stringBuffer.append((z4 ? rb.getString(OperatorName.CLOSE_AND_STROKE) : rb.getString("SPACE")) + (manifest.getAttributes(name2) != null || manifest.getAttributes(new StringBuilder().append("./").append(name2).toString()) != null || manifest.getAttributes(new StringBuilder().append(RuntimeConstants.SIG_PACKAGE).append(name2).toString()) != null ? rb.getString("m") : rb.getString("SPACE")) + (z5 ? rb.getString(OperatorName.NON_STROKING_CMYK) : rb.getString("SPACE")) + (z6 ? rb.getString(OperatorName.SET_FLATNESS) : rb.getString("SPACE")) + ((inKeyStore & 4) != 0 ? "X" : " ") + rb.getString("SPACE"));
                            stringBuffer.append(CallSiteDescriptor.OPERATOR_DELIMITER);
                        }
                        if (z4) {
                            if (this.showcerts) {
                                stringBuffer.append('\n');
                            }
                            for (CodeSigner codeSigner : codeSigners) {
                                String signerInfo = signerInfo(codeSigner, string);
                                if (this.showcerts) {
                                    stringBuffer.append(signerInfo);
                                    stringBuffer.append('\n');
                                }
                            }
                        } else if (this.showcerts && !this.verbose.equals("all")) {
                            if (signatureRelated(name2)) {
                                stringBuffer.append("\n" + string + rb.getString(".Signature.related.entries.") + "\n\n");
                            } else {
                                stringBuffer.append("\n" + string + rb.getString(".Unsigned.entries.") + "\n\n");
                            }
                        }
                        if (this.verbose != null) {
                            String stringBuffer2 = stringBuffer.toString();
                            if (signatureRelated(name2)) {
                                stringBuffer2 = TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR + stringBuffer2;
                            }
                            if (!linkedHashMap.containsKey(stringBuffer2)) {
                                linkedHashMap.put(stringBuffer2, new ArrayList());
                            }
                            StringBuffer stringBuffer3 = new StringBuffer();
                            String l = Long.toString(jarEntry.getSize());
                            for (int length = 6 - l.length(); length > 0; length--) {
                                stringBuffer3.append(' ');
                            }
                            stringBuffer3.append(l).append(' ').append(new Date(jarEntry.getTime()).toString());
                            stringBuffer3.append(' ').append(name2);
                            ((List) linkedHashMap.get(stringBuffer2)).add(stringBuffer3.toString());
                        }
                    }
                }
                if (this.verbose != null) {
                    for (Map.Entry entry : linkedHashMap.entrySet()) {
                        List list = (List) entry.getValue();
                        String str2 = (String) entry.getKey();
                        if (str2.charAt(0) == '-') {
                            str2 = str2.substring(1);
                        }
                        int indexOf = str2.indexOf(124);
                        if (this.verbose.equals("all")) {
                            Iterator it2 = list.iterator();
                            while (it2.hasNext()) {
                                System.out.println(str2.substring(0, indexOf) + ((String) it2.next()));
                                System.out.printf(str2.substring(indexOf + 1), new Object[0]);
                            }
                        } else {
                            if (this.verbose.equals("grouped")) {
                                Iterator it3 = list.iterator();
                                while (it3.hasNext()) {
                                    System.out.println(str2.substring(0, indexOf) + ((String) it3.next()));
                                }
                            } else if (this.verbose.equals("summary")) {
                                System.out.print(str2.substring(0, indexOf));
                                if (list.size() > 1) {
                                    System.out.println(((String) list.get(0)) + " " + String.format(rb.getString(".and.d.more."), Integer.valueOf(list.size() - 1)));
                                } else {
                                    System.out.println((String) list.get(0));
                                }
                            }
                            System.out.printf(str2.substring(indexOf + 1), new Object[0]);
                        }
                    }
                    System.out.println();
                    System.out.println(rb.getString(".s.signature.was.verified."));
                    System.out.println(rb.getString(".m.entry.is.listed.in.manifest"));
                    System.out.println(rb.getString(".k.at.least.one.certificate.was.found.in.keystore"));
                    System.out.println(rb.getString(".i.at.least.one.certificate.was.found.in.identity.scope"));
                    if (this.ckaliases.size() > 0) {
                        System.out.println(rb.getString(".X.not.signed.by.specified.alias.es."));
                    }
                }
                if (manifest == null) {
                    System.out.println();
                    System.out.println(rb.getString("no.manifest."));
                }
                if (!hashMap.isEmpty() || !hashMap2.isEmpty() || !hashMap4.isEmpty()) {
                    if (this.verbose != null) {
                        System.out.println();
                    }
                    for (String str3 : hashMap2.keySet()) {
                        if (!hashMap.containsKey(str3)) {
                            hashMap4.putIfAbsent(str3, String.format(rb.getString("history.nosf"), str3));
                        }
                    }
                    for (String str4 : hashMap.keySet()) {
                        PKCS7 pkcs7 = (PKCS7) hashMap2.get(str4);
                        if (pkcs7 != null) {
                            try {
                                SignerInfo signerInfo2 = pkcs7.getSignerInfos()[0];
                                X509Certificate certificate = signerInfo2.getCertificate(pkcs7);
                                String str5 = (String) hashMap.get(str4);
                                String makeSigAlg = AlgorithmId.makeSigAlg(signerInfo2.getDigestAlgorithmId().getName(), signerInfo2.getDigestEncryptionAlgorithmId().getName());
                                PublicKey publicKey = certificate.getPublicKey();
                                PKCS7 tsToken = signerInfo2.getTsToken();
                                if (tsToken != null) {
                                    this.hasTimestampBlock = true;
                                    SignerInfo signerInfo3 = tsToken.getSignerInfos()[0];
                                    X509Certificate certificate2 = signerInfo3.getCertificate(tsToken);
                                    TimestampToken timestampToken = new TimestampToken(tsToken.getContentInfo().getData());
                                    PublicKey publicKey2 = certificate2.getPublicKey();
                                    String name3 = timestampToken.getHashAlgorithm().getName();
                                    String makeSigAlg2 = AlgorithmId.makeSigAlg(signerInfo3.getDigestAlgorithmId().getName(), signerInfo3.getDigestEncryptionAlgorithmId().getName());
                                    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"), Locale.getDefault(Locale.Category.FORMAT));
                                    calendar.setTime(timestampToken.getDate());
                                    format = String.format(rb.getString("history.with.ts"), certificate.getSubjectX500Principal(), verifyWithWeak(str5, DIGEST_PRIMITIVE_SET, false), verifyWithWeak(makeSigAlg, SIG_PRIMITIVE_SET, false), verifyWithWeak(publicKey), calendar, certificate2.getSubjectX500Principal(), verifyWithWeak(name3, DIGEST_PRIMITIVE_SET, true), verifyWithWeak(makeSigAlg2, SIG_PRIMITIVE_SET, true), verifyWithWeak(publicKey2));
                                } else {
                                    format = String.format(rb.getString("history.without.ts"), certificate.getSubjectX500Principal(), verifyWithWeak(str5, DIGEST_PRIMITIVE_SET, false), verifyWithWeak(makeSigAlg, SIG_PRIMITIVE_SET, false), verifyWithWeak(publicKey));
                                }
                            } catch (Exception e2) {
                                format = String.format(rb.getString("history.unparsable"), hashMap3.get(str4));
                            }
                            if (this.verbose != null) {
                                System.out.println(format);
                            }
                        } else {
                            hashMap4.putIfAbsent(str4, String.format(rb.getString("history.nobk"), str4));
                        }
                    }
                    if (this.verbose != null) {
                        Iterator it4 = hashMap4.keySet().iterator();
                        while (it4.hasNext()) {
                            System.out.println((String) hashMap4.get((String) it4.next()));
                        }
                    }
                }
                System.out.println();
                if (!this.aliasNotInStore && this.keystore != null) {
                    this.signerSelfSigned = false;
                }
                if (!z) {
                    if (this.disabledAlgFound) {
                        if (this.verbose != null) {
                            System.out.println(rb.getString("jar.treated.unsigned.see.weak.verbose"));
                            System.out.println("\n  jdk.jar.disabledAlgorithms=" + Security.getProperty("jdk.jar.disabledAlgorithms"));
                        } else {
                            System.out.println(rb.getString("jar.treated.unsigned.see.weak"));
                        }
                    } else if (z3) {
                        System.out.println(rb.getString("jar.treated.unsigned"));
                    } else {
                        System.out.println(rb.getString("jar.is.unsigned"));
                    }
                } else {
                    displayMessagesAndResult(false);
                }
                if (jarFile2 == null) {
                    return;
                }
                jarFile2.close();
            } catch (Exception e3) {
                System.out.println(rb.getString("jarsigner.") + e3);
                if (this.debug) {
                    e3.printStackTrace();
                }
                if (0 != 0) {
                    jarFile.close();
                }
                System.exit(1);
            }
        } catch (Throwable th6) {
            if (0 != 0) {
                jarFile.close();
            }
            throw th6;
        }
    }

    private void displayMessagesAndResult(boolean z) {
        String string;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        boolean z2 = this.expireDate == null || this.expireDate.after(new Date());
        if (this.badKeyUsage || this.badExtendedKeyUsage || this.badNetscapeCertType || this.notYetValidCert || this.chainNotValidated || this.hasExpiredCert || this.hasUnsignedEntry || this.signerSelfSigned || this.legacyAlg != 0 || this.disabledAlg != 0 || this.aliasNotInStore || this.notSignedByAlias || this.tsaChainNotValidated || (this.hasExpiredTsaCert && !z2)) {
            if (this.strict) {
                string = rb.getString(z ? "jar.signed.with.signer.errors." : "jar.verified.with.signer.errors.");
            } else {
                string = rb.getString(z ? "jar.signed." : "jar.verified.");
            }
            if (this.badKeyUsage) {
                arrayList.add(rb.getString(z ? "The.signer.certificate.s.KeyUsage.extension.doesn.t.allow.code.signing." : "This.jar.contains.entries.whose.signer.certificate.s.KeyUsage.extension.doesn.t.allow.code.signing."));
            }
            if (this.badExtendedKeyUsage) {
                arrayList.add(rb.getString(z ? "The.signer.certificate.s.ExtendedKeyUsage.extension.doesn.t.allow.code.signing." : "This.jar.contains.entries.whose.signer.certificate.s.ExtendedKeyUsage.extension.doesn.t.allow.code.signing."));
            }
            if (this.badNetscapeCertType) {
                arrayList.add(rb.getString(z ? "The.signer.certificate.s.NetscapeCertType.extension.doesn.t.allow.code.signing." : "This.jar.contains.entries.whose.signer.certificate.s.NetscapeCertType.extension.doesn.t.allow.code.signing."));
            }
            if (this.hasUnsignedEntry) {
                arrayList.add(rb.getString("This.jar.contains.unsigned.entries.which.have.not.been.integrity.checked."));
            }
            if (this.hasExpiredCert) {
                arrayList.add(rb.getString(z ? "The.signer.certificate.has.expired." : "This.jar.contains.entries.whose.signer.certificate.has.expired."));
            }
            if (this.notYetValidCert) {
                arrayList.add(rb.getString(z ? "The.signer.certificate.is.not.yet.valid." : "This.jar.contains.entries.whose.signer.certificate.is.not.yet.valid."));
            }
            if (this.chainNotValidated) {
                arrayList.add(String.format(rb.getString(z ? "The.signer.s.certificate.chain.is.invalid.reason.1" : "This.jar.contains.entries.whose.certificate.chain.is.invalid.reason.1"), this.chainNotValidatedReason.getLocalizedMessage()));
            }
            if (this.hasExpiredTsaCert) {
                arrayList.add(rb.getString("The.timestamp.has.expired."));
            }
            if (this.tsaChainNotValidated) {
                arrayList.add(String.format(rb.getString(z ? "The.tsa.certificate.chain.is.invalid.reason.1" : "This.jar.contains.entries.whose.tsa.certificate.chain.is.invalid.reason.1"), this.tsaChainNotValidatedReason.getLocalizedMessage()));
            }
            if (this.notSignedByAlias) {
                arrayList.add(rb.getString("This.jar.contains.signed.entries.which.is.not.signed.by.the.specified.alias.es."));
            }
            if (this.aliasNotInStore) {
                arrayList.add(rb.getString("This.jar.contains.signed.entries.that.s.not.signed.by.alias.in.this.keystore."));
            }
            if (this.signerSelfSigned) {
                arrayList.add(rb.getString(z ? "The.signer.s.certificate.is.self.signed." : "This.jar.contains.entries.whose.signer.certificate.is.self.signed."));
            }
            if (z) {
                if ((this.legacyAlg & 1) == 1) {
                    arrayList2.add(String.format(rb.getString("The.1.algorithm.specified.for.the.2.option.is.considered.a.security.risk..This.algorithm.will.be.disabled.in.a.future.update."), this.digestalg, "-digestalg"));
                }
                if ((this.disabledAlg & 1) == 1) {
                    arrayList.add(String.format(rb.getString("The.1.algorithm.specified.for.the.2.option.is.considered.a.security.risk.and.is.disabled."), this.digestalg, "-digestalg"));
                }
                if ((this.legacyAlg & 2) == 2) {
                    arrayList2.add(String.format(rb.getString("The.1.algorithm.specified.for.the.2.option.is.considered.a.security.risk..This.algorithm.will.be.disabled.in.a.future.update."), this.sigalg, "-sigalg"));
                }
                if ((this.disabledAlg & 2) == 2) {
                    arrayList.add(String.format(rb.getString("The.1.algorithm.specified.for.the.2.option.is.considered.a.security.risk.and.is.disabled."), this.sigalg, "-sigalg"));
                }
                if ((this.legacyAlg & 4) == 4) {
                    arrayList2.add(String.format(rb.getString("The.1.algorithm.specified.for.the.2.option.is.considered.a.security.risk..This.algorithm.will.be.disabled.in.a.future.update."), this.tSADigestAlg, "-tsadigestalg"));
                }
                if ((this.disabledAlg & 4) == 4) {
                    arrayList.add(String.format(rb.getString("The.1.algorithm.specified.for.the.2.option.is.considered.a.security.risk.and.is.disabled."), this.tSADigestAlg, "-tsadigestalg"));
                }
                if ((this.legacyAlg & 8) == 8) {
                    arrayList2.add(String.format(rb.getString("The.1.signing.key.has.a.keysize.of.2.which.is.considered.a.security.risk..This.key.size.will.be.disabled.in.a.future.update."), this.privateKey.getAlgorithm(), Integer.valueOf(KeyUtil.getKeySize(this.privateKey))));
                }
                if ((this.disabledAlg & 8) == 8) {
                    arrayList.add(String.format(rb.getString("The.1.signing.key.has.a.keysize.of.2.which.is.considered.a.security.risk.and.is.disabled."), this.privateKey.getAlgorithm(), Integer.valueOf(KeyUtil.getKeySize(this.privateKey))));
                }
            } else {
                if ((this.legacyAlg & 1) != 0) {
                    arrayList2.add(String.format(rb.getString("The.digest.algorithm.1.is.considered.a.security.risk..This.algorithm.will.be.disabled.in.a.future.update."), this.legacyDigestAlg));
                }
                if ((this.legacyAlg & 2) == 2) {
                    arrayList2.add(String.format(rb.getString("The.signature.algorithm.1.is.considered.a.security.risk..This.algorithm.will.be.disabled.in.a.future.update."), this.legacySigAlg));
                }
                if ((this.legacyAlg & 4) != 0) {
                    arrayList2.add(String.format(rb.getString("The.timestamp.digest.algorithm.1.is.considered.a.security.risk..This.algorithm.will.be.disabled.in.a.future.update."), this.legacyTsaDigestAlg));
                }
                if ((this.legacyAlg & 8) == 8) {
                    arrayList2.add(String.format(rb.getString("The.1.signing.key.has.a.keysize.of.2.which.is.considered.a.security.risk..This.key.size.will.be.disabled.in.a.future.update."), this.weakPublicKey.getAlgorithm(), Integer.valueOf(KeyUtil.getKeySize(this.weakPublicKey))));
                }
            }
        } else {
            string = rb.getString(z ? "jar.signed." : "jar.verified.");
        }
        if (this.hasExpiredTsaCert) {
            this.hasExpiringTsaCert = false;
        }
        if (this.hasExpiringCert || ((this.hasExpiringTsaCert && this.expireDate != null) || ((this.noTimestamp && this.expireDate != null) || (this.hasExpiredTsaCert && z2)))) {
            if (this.hasExpiredTsaCert && z2) {
                if (this.expireDate != null) {
                    arrayList2.add(String.format(rb.getString("The.timestamp.expired.1.but.usable.2"), this.tsaExpireDate, this.expireDate));
                }
                this.hasExpiredTsaCert = false;
            }
            if (this.hasExpiringCert) {
                arrayList2.add(rb.getString(z ? "The.signer.certificate.will.expire.within.six.months." : "This.jar.contains.entries.whose.signer.certificate.will.expire.within.six.months."));
            }
            if (this.hasExpiringTsaCert && this.expireDate != null) {
                if (this.expireDate.after(this.tsaExpireDate)) {
                    arrayList2.add(String.format(rb.getString("The.timestamp.will.expire.within.one.year.on.1.but.2"), this.tsaExpireDate, this.expireDate));
                } else {
                    arrayList2.add(String.format(rb.getString("The.timestamp.will.expire.within.one.year.on.1"), this.tsaExpireDate));
                }
            }
            if (this.noTimestamp && this.expireDate != null) {
                if (this.hasTimestampBlock) {
                    arrayList2.add(String.format(rb.getString(z ? "invalid.timestamp.signing" : "bad.timestamp.verifying"), this.expireDate));
                } else {
                    arrayList2.add(String.format(rb.getString(z ? "no.timestamp.signing" : "no.timestamp.verifying"), this.expireDate));
                }
            }
        }
        System.out.println(string);
        if (this.strict) {
            if (!arrayList.isEmpty()) {
                System.out.println();
                System.out.println(rb.getString("Error."));
                PrintStream printStream = System.out;
                printStream.getClass();
                arrayList.forEach(printStream::println);
            }
            if (!arrayList2.isEmpty()) {
                System.out.println();
                System.out.println(rb.getString("Warning."));
                PrintStream printStream2 = System.out;
                printStream2.getClass();
                arrayList2.forEach(printStream2::println);
            }
        } else if (!arrayList.isEmpty() || !arrayList2.isEmpty()) {
            System.out.println();
            System.out.println(rb.getString("Warning."));
            PrintStream printStream3 = System.out;
            printStream3.getClass();
            arrayList.forEach(printStream3::println);
            PrintStream printStream4 = System.out;
            printStream4.getClass();
            arrayList2.forEach(printStream4::println);
        }
        if (!z && ((!arrayList.isEmpty() || !arrayList2.isEmpty()) && (this.verbose == null || !this.showcerts))) {
            System.out.println();
            System.out.println(rb.getString("Re.run.with.the.verbose.and.certs.options.for.more.details."));
        }
        if (z || this.verbose != null) {
            if (!this.hasExpiringCert && !this.hasExpiredCert && this.expireDate != null && z2) {
                arrayList3.add(String.format(rb.getString("The.signer.certificate.will.expire.on.1."), this.expireDate));
            }
            if (!this.noTimestamp && !this.hasExpiringTsaCert && !this.hasExpiredTsaCert && this.tsaExpireDate != null) {
                if (z2) {
                    arrayList3.add(String.format(rb.getString("The.timestamp.will.expire.on.1."), this.tsaExpireDate));
                } else {
                    arrayList3.add(String.format(rb.getString("signer.cert.expired.1.but.timestamp.good.2."), this.expireDate, this.tsaExpireDate));
                }
            }
        }
        if (!arrayList3.isEmpty()) {
            System.out.println();
            PrintStream printStream5 = System.out;
            printStream5.getClass();
            arrayList3.forEach(printStream5::println);
        }
    }

    private String verifyWithWeak(String str, Set<CryptoPrimitive> set, boolean z) {
        if (DISABLED_CHECK.permits(set, str, (AlgorithmParameters) null)) {
            if (LEGACY_CHECK.permits(set, str, (AlgorithmParameters) null)) {
                return str;
            }
            if (set == SIG_PRIMITIVE_SET) {
                this.legacyAlg |= 2;
                this.legacySigAlg = str;
            } else if (z) {
                this.legacyAlg |= 4;
                this.legacyTsaDigestAlg = str;
            } else {
                this.legacyAlg |= 1;
                this.legacyDigestAlg = str;
            }
            return String.format(rb.getString("with.weak"), str);
        }
        this.disabledAlgFound = true;
        return String.format(rb.getString("with.disabled"), str);
    }

    private String verifyWithWeak(PublicKey publicKey) {
        int keySize = KeyUtil.getKeySize(publicKey);
        if (DISABLED_CHECK.permits(SIG_PRIMITIVE_SET, publicKey)) {
            if (LEGACY_CHECK.permits(SIG_PRIMITIVE_SET, publicKey)) {
                return keySize >= 0 ? String.format(rb.getString("key.bit"), Integer.valueOf(keySize)) : rb.getString("unknown.size");
            }
            this.weakPublicKey = publicKey;
            this.legacyAlg |= 8;
            return String.format(rb.getString("key.bit.weak"), Integer.valueOf(keySize));
        }
        this.disabledAlgFound = true;
        return String.format(rb.getString("key.bit.disabled"), Integer.valueOf(keySize));
    }

    private void checkWeakSign(String str, Set<CryptoPrimitive> set, boolean z) {
        if (DISABLED_CHECK.permits(set, str, (AlgorithmParameters) null)) {
            if (!LEGACY_CHECK.permits(set, str, (AlgorithmParameters) null)) {
                if (set == SIG_PRIMITIVE_SET) {
                    this.legacyAlg |= 2;
                    return;
                } else if (z) {
                    this.legacyAlg |= 4;
                    return;
                } else {
                    this.legacyAlg |= 1;
                    return;
                }
            }
            return;
        }
        if (set == SIG_PRIMITIVE_SET) {
            this.disabledAlg |= 2;
        } else if (z) {
            this.disabledAlg |= 4;
        } else {
            this.disabledAlg |= 1;
        }
    }

    private void checkWeakSign(PrivateKey privateKey) {
        if (DISABLED_CHECK.permits(SIG_PRIMITIVE_SET, privateKey)) {
            if (!LEGACY_CHECK.permits(SIG_PRIMITIVE_SET, privateKey)) {
                this.legacyAlg |= 8;
                return;
            }
            return;
        }
        this.disabledAlg |= 8;
    }

    String printCert(boolean z, String str, Certificate certificate, Date date, boolean z2) throws Exception {
        StringBuilder sb = new StringBuilder();
        String string = rb.getString("SPACE");
        X509Certificate x509Certificate = null;
        if (certificate instanceof X509Certificate) {
            x509Certificate = (X509Certificate) certificate;
            sb.append(str).append(x509Certificate.getType()).append(rb.getString("COMMA")).append(x509Certificate.getSubjectDN().getName());
        } else {
            sb.append(str).append(certificate.getType());
        }
        String str2 = this.storeHash.get(certificate);
        if (str2 != null) {
            sb.append(string).append(str2);
        }
        if (x509Certificate != null) {
            sb.append("\n").append(str).append(RuntimeConstants.SIG_ARRAY);
            if (this.trustedCerts.contains(x509Certificate)) {
                sb.append(rb.getString("trusted.certificate"));
            } else {
                Date notAfter = x509Certificate.getNotAfter();
                boolean z3 = true;
                try {
                    if (z) {
                        if (this.tsaExpireDate == null || this.tsaExpireDate.after(notAfter)) {
                            this.tsaExpireDate = notAfter;
                        }
                    } else if (this.expireDate == null || this.expireDate.after(notAfter)) {
                        this.expireDate = notAfter;
                    }
                    if (date == null) {
                        x509Certificate.checkValidity();
                        if (notAfter.getTime() < System.currentTimeMillis() + (z ? ONE_YEAR : SIX_MONTHS)) {
                            if (z) {
                                this.hasExpiringTsaCert = true;
                            } else {
                                this.hasExpiringCert = true;
                            }
                            if (expiringTimeForm == null) {
                                expiringTimeForm = new MessageFormat(rb.getString("certificate.will.expire.on"));
                            }
                            sb.append(expiringTimeForm.format(new Object[]{notAfter}));
                            z3 = false;
                        }
                    } else {
                        x509Certificate.checkValidity(date);
                    }
                    if (z3) {
                        if (validityTimeForm == null) {
                            validityTimeForm = new MessageFormat(rb.getString("certificate.is.valid.from"));
                        }
                        sb.append(validityTimeForm.format(new Object[]{x509Certificate.getNotBefore(), notAfter}));
                    }
                } catch (CertificateExpiredException e) {
                    if (z) {
                        this.hasExpiredTsaCert = true;
                    } else {
                        this.hasExpiredCert = true;
                    }
                    if (expiredTimeForm == null) {
                        expiredTimeForm = new MessageFormat(rb.getString("certificate.expired.on"));
                    }
                    sb.append(expiredTimeForm.format(new Object[]{notAfter}));
                } catch (CertificateNotYetValidException e2) {
                    if (!z) {
                        this.notYetValidCert = true;
                    }
                    if (notYetTimeForm == null) {
                        notYetTimeForm = new MessageFormat(rb.getString("certificate.is.not.valid.until"));
                    }
                    sb.append(notYetTimeForm.format(new Object[]{x509Certificate.getNotBefore()}));
                }
            }
            sb.append("]");
            if (z2) {
                boolean[] zArr = new boolean[3];
                checkCertUsage(x509Certificate, zArr);
                if (zArr[0] || zArr[1] || zArr[2]) {
                    String str3 = "";
                    if (zArr[0]) {
                        str3 = "KeyUsage";
                    }
                    if (zArr[1]) {
                        if (str3.length() > 0) {
                            str3 = str3 + ", ";
                        }
                        str3 = str3 + "ExtendedKeyUsage";
                    }
                    if (zArr[2]) {
                        if (str3.length() > 0) {
                            str3 = str3 + ", ";
                        }
                        str3 = str3 + "NetscapeCertType";
                    }
                    sb.append("\n").append(str).append(MessageFormat.format(rb.getString(".{0}.extension.does.not.support.code.signing."), str3));
                }
            }
        }
        return sb.toString();
    }

    private String printTimestamp(String str, Timestamp timestamp) {
        if (signTimeForm == null) {
            signTimeForm = new MessageFormat(rb.getString("entry.was.signed.on"));
        }
        return str + RuntimeConstants.SIG_ARRAY + signTimeForm.format(new Object[]{timestamp.getTimestamp()}) + "]";
    }

    private int inKeyStoreForOneSigner(CodeSigner codeSigner) {
        if (this.cacheForInKS.containsKey(codeSigner)) {
            return this.cacheForInKS.get(codeSigner).intValue();
        }
        int i = 0;
        for (Certificate certificate : codeSigner.getSignerCertPath().getCertificates()) {
            String str = this.storeHash.get(certificate);
            if (str != null) {
                if (str.startsWith(RuntimeConstants.SIG_METHOD)) {
                    i |= 1;
                } else if (str.startsWith(RuntimeConstants.SIG_ARRAY)) {
                    i |= 2;
                }
                if (this.ckaliases.contains(str.substring(1, str.length() - 1))) {
                    i |= 8;
                }
            } else {
                if (this.store != null) {
                    try {
                        str = this.store.getCertificateAlias(certificate);
                    } catch (KeyStoreException e) {
                    }
                    if (str != null) {
                        this.storeHash.put(certificate, RuntimeConstants.SIG_METHOD + str + RuntimeConstants.SIG_ENDMETHOD);
                        i |= 1;
                    }
                }
                if (this.ckaliases.contains(str)) {
                    i |= 8;
                }
            }
        }
        this.cacheForInKS.put(codeSigner, Integer.valueOf(i));
        return i;
    }

    int inKeyStore(CodeSigner[] codeSignerArr) {
        if (codeSignerArr == null) {
            return 0;
        }
        int i = 0;
        for (CodeSigner codeSigner : codeSignerArr) {
            i |= inKeyStoreForOneSigner(codeSigner);
        }
        if (this.ckaliases.size() > 0 && (i & 8) == 0) {
            i |= 4;
        }
        return i;
    }

    void signJar(String str, String str2, String[] strArr) throws Exception {
        JarFile jarFile;
        Throwable th;
        checkWeakSign(this.digestalg, DIGEST_PRIMITIVE_SET, false);
        checkWeakSign(this.tSADigestAlg, DIGEST_PRIMITIVE_SET, true);
        if (this.sigalg == null) {
            this.sigalg = getDefaultSigAlgForKey(this.privateKey.getAlgorithm());
        }
        checkWeakSign(this.sigalg, SIG_PRIMITIVE_SET, false);
        checkWeakSign(this.privateKey);
        boolean z = false;
        X509Certificate x509Certificate = null;
        if (this.sigfile == null) {
            this.sigfile = str2;
            z = true;
        }
        if (this.sigfile.length() > 8) {
            this.sigfile = this.sigfile.substring(0, 8).toUpperCase(Locale.ENGLISH);
        } else {
            this.sigfile = this.sigfile.toUpperCase(Locale.ENGLISH);
        }
        StringBuilder sb = new StringBuilder(this.sigfile.length());
        for (int i = 0; i < this.sigfile.length(); i++) {
            char charAt = this.sigfile.charAt(i);
            if ((charAt < 'A' || charAt > 'Z') && ((charAt < '0' || charAt > '9') && charAt != '-' && charAt != '_')) {
                if (z) {
                    charAt = '_';
                } else {
                    throw new RuntimeException(rb.getString("signature.filename.must.consist.of.the.following.characters.A.Z.0.9.or."));
                }
            }
            sb.append(charAt);
        }
        this.sigfile = sb.toString();
        String str3 = this.signedjar == null ? str + ".sig" : this.signedjar;
        File file = new File(str);
        File file2 = new File(str3);
        try {
            this.zipFile = new ZipFile(str);
        } catch (IOException e) {
            error(rb.getString("unable.to.open.jar.file.") + str, e);
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file2);
        } catch (IOException e2) {
            error(rb.getString("unable.to.create.") + str3, e2);
        }
        ZipOutputStream zipOutputStream = new ZipOutputStream(new PrintStream(fileOutputStream));
        (META_INF + this.sigfile + ".SF").toUpperCase(Locale.ENGLISH);
        (META_INF + this.sigfile + ".DSA").toUpperCase(Locale.ENGLISH);
        Manifest manifest = new Manifest();
        Map<String, Attributes> entries = manifest.getEntries();
        Attributes attributes = null;
        boolean z2 = false;
        boolean z3 = false;
        byte[] bArr = null;
        try {
            try {
                MessageDigest[] messageDigestArr = {MessageDigest.getInstance(this.digestalg)};
                ZipEntry manifestFile = getManifestFile(this.zipFile);
                ZipEntry zipEntry = manifestFile;
                if (manifestFile != null) {
                    bArr = getBytes(this.zipFile, zipEntry);
                    manifest.read(new ByteArrayInputStream(bArr));
                    attributes = (Attributes) manifest.getMainAttributes().clone();
                } else {
                    Attributes mainAttributes = manifest.getMainAttributes();
                    mainAttributes.putValue(Attributes.Name.MANIFEST_VERSION.toString(), VERSION);
                    mainAttributes.putValue("Created-By", System.getProperty("java.version") + " (" + System.getProperty("java.vendor") + RuntimeConstants.SIG_ENDMETHOD);
                    zipEntry = new ZipEntry("META-INF/MANIFEST.MF");
                    z3 = true;
                }
                Vector vector = new Vector();
                boolean z4 = false;
                Enumeration<? extends ZipEntry> entries2 = this.zipFile.entries();
                while (entries2.hasMoreElements()) {
                    ZipEntry nextElement = entries2.nextElement();
                    if (nextElement.getName().startsWith(META_INF)) {
                        vector.addElement(nextElement);
                        if (SignatureFileVerifier.isBlockOrSF(nextElement.getName().toUpperCase(Locale.ENGLISH))) {
                            z4 = true;
                        }
                        if (signatureRelated(nextElement.getName())) {
                        }
                    }
                    if (manifest.getAttributes(nextElement.getName()) != null) {
                        if (updateDigests(nextElement, this.zipFile, messageDigestArr, manifest)) {
                            z2 = true;
                        }
                    } else if (!nextElement.isDirectory()) {
                        entries.put(nextElement.getName(), getDigestAttributes(nextElement, this.zipFile, messageDigestArr));
                        z2 = true;
                    }
                }
                if (z2) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    manifest.write(byteArrayOutputStream);
                    if (z4) {
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        if (bArr != null && attributes.equals(manifest.getMainAttributes())) {
                            int findHeaderEnd = findHeaderEnd(byteArray);
                            int findHeaderEnd2 = findHeaderEnd(bArr);
                            if (findHeaderEnd == findHeaderEnd2) {
                                System.arraycopy(bArr, 0, byteArray, 0, findHeaderEnd2);
                            } else {
                                byte[] bArr2 = new byte[(findHeaderEnd2 + byteArray.length) - findHeaderEnd];
                                System.arraycopy(bArr, 0, bArr2, 0, findHeaderEnd2);
                                System.arraycopy(byteArray, findHeaderEnd, bArr2, findHeaderEnd2, byteArray.length - findHeaderEnd);
                                byteArray = bArr2;
                            }
                        }
                        bArr = byteArray;
                    } else {
                        bArr = byteArrayOutputStream.toByteArray();
                    }
                }
                if (z2) {
                    zipEntry = new ZipEntry("META-INF/MANIFEST.MF");
                }
                if (this.verbose != null) {
                    if (z3) {
                        System.out.println(rb.getString(".adding.") + zipEntry.getName());
                    } else if (z2) {
                        System.out.println(rb.getString(".updating.") + zipEntry.getName());
                    }
                }
                zipOutputStream.putNextEntry(zipEntry);
                zipOutputStream.write(bArr);
                SignatureFile signatureFile = new SignatureFile(messageDigestArr, manifest, new ManifestDigester(bArr), this.sigfile, this.signManifest);
                if (this.tsaAlias != null) {
                    x509Certificate = getTsaCert(this.tsaAlias);
                }
                if (this.tsaUrl == null && x509Certificate == null) {
                    this.noTimestamp = true;
                }
                SignatureFile.Block block = null;
                try {
                    block = signatureFile.generateBlock(this.privateKey, this.sigalg, this.certChain, this.externalSF, this.tsaUrl, x509Certificate, this.tSAPolicyID, this.tSADigestAlg, this.signingMechanism, strArr, this.zipFile);
                } catch (SocketTimeoutException e3) {
                    error(rb.getString("unable.to.sign.jar.") + rb.getString("no.response.from.the.Timestamping.Authority.") + "\n  -J-Dhttp.proxyHost=<hostname>\n  -J-Dhttp.proxyPort=<portnumber>\n" + rb.getString("or") + "\n  -J-Dhttps.proxyHost=<hostname> \n  -J-Dhttps.proxyPort=<portnumber> ", e3);
                }
                String metaName = signatureFile.getMetaName();
                String metaName2 = block.getMetaName();
                ZipEntry zipEntry2 = new ZipEntry(metaName);
                ZipEntry zipEntry3 = new ZipEntry(metaName2);
                long currentTimeMillis = System.currentTimeMillis();
                zipEntry2.setTime(currentTimeMillis);
                zipEntry3.setTime(currentTimeMillis);
                zipOutputStream.putNextEntry(zipEntry2);
                signatureFile.write(zipOutputStream);
                if (this.verbose != null) {
                    if (this.zipFile.getEntry(metaName) != null) {
                        System.out.println(rb.getString(".updating.") + metaName);
                    } else {
                        System.out.println(rb.getString(".adding.") + metaName);
                    }
                }
                if (this.verbose != null) {
                    if (this.tsaUrl != null || x509Certificate != null) {
                        System.out.println(rb.getString("requesting.a.signature.timestamp"));
                    }
                    if (this.tsaUrl != null) {
                        System.out.println(rb.getString("TSA.location.") + this.tsaUrl);
                    }
                    if (x509Certificate != null) {
                        URI timestampingURI = TimestampedSigner.getTimestampingURI(x509Certificate);
                        if (timestampingURI != null) {
                            System.out.println(rb.getString("TSA.location.") + timestampingURI);
                        }
                        System.out.println(rb.getString("TSA.certificate.") + printCert(true, "", x509Certificate, null, false));
                    }
                    if (this.signingMechanism != null) {
                        System.out.println(rb.getString("using.an.alternative.signing.mechanism"));
                    }
                }
                zipOutputStream.putNextEntry(zipEntry3);
                block.write(zipOutputStream);
                if (this.verbose != null) {
                    if (this.zipFile.getEntry(metaName2) != null) {
                        System.out.println(rb.getString(".updating.") + metaName2);
                    } else {
                        System.out.println(rb.getString(".adding.") + metaName2);
                    }
                }
                for (int i2 = 0; i2 < vector.size(); i2++) {
                    ZipEntry zipEntry4 = (ZipEntry) vector.elementAt(i2);
                    if (!zipEntry4.getName().equalsIgnoreCase("META-INF/MANIFEST.MF") && !zipEntry4.getName().equalsIgnoreCase(metaName) && !zipEntry4.getName().equalsIgnoreCase(metaName2)) {
                        writeEntry(this.zipFile, zipOutputStream, zipEntry4);
                    }
                }
                Enumeration<? extends ZipEntry> entries3 = this.zipFile.entries();
                while (entries3.hasMoreElements()) {
                    ZipEntry nextElement2 = entries3.nextElement();
                    if (!nextElement2.getName().startsWith(META_INF)) {
                        if (this.verbose != null) {
                            if (manifest.getAttributes(nextElement2.getName()) != null) {
                                System.out.println(rb.getString(".signing.") + nextElement2.getName());
                            } else {
                                System.out.println(rb.getString(".adding.") + nextElement2.getName());
                            }
                        }
                        writeEntry(this.zipFile, zipOutputStream, nextElement2);
                    }
                }
                if (this.zipFile != null) {
                    this.zipFile.close();
                    this.zipFile = null;
                }
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }
            } catch (IOException e4) {
                error(rb.getString("unable.to.sign.jar.") + e4, e4);
                if (this.zipFile != null) {
                    this.zipFile.close();
                    this.zipFile = null;
                }
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }
            }
            try {
                jarFile = new JarFile(file2);
                th = null;
            } catch (Exception e5) {
                if (this.debug) {
                    e5.printStackTrace();
                }
            }
            try {
                try {
                    Timestamp timestamp = null;
                    try {
                        SignerInfo signerInfo = new PKCS7(jarFile.getInputStream(jarFile.getEntry(META_INF + this.sigfile + Constants.NAME_SEPARATOR + this.privateKey.getAlgorithm()))).getSignerInfos()[0];
                        if (signerInfo.getTsToken() != null) {
                            this.hasTimestampBlock = true;
                        }
                        timestamp = signerInfo.getTimestamp();
                    } catch (Exception e6) {
                        this.tsaChainNotValidated = true;
                        this.tsaChainNotValidatedReason = e6;
                    }
                    String certsAndTSInfo = certsAndTSInfo("", "    ", Arrays.asList(this.certChain), timestamp);
                    if (this.verbose != null) {
                        System.out.println(certsAndTSInfo);
                    }
                    if (jarFile != null) {
                        if (0 != 0) {
                            try {
                                jarFile.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        } else {
                            jarFile.close();
                        }
                    }
                    if (this.signedjar == null && !file2.renameTo(file)) {
                        File file3 = new File(str + ".orig");
                        if (file.renameTo(file3)) {
                            if (file2.renameTo(file)) {
                                file3.delete();
                            } else {
                                error(new MessageFormat(rb.getString("attempt.to.rename.signedJarFile.to.jarFile.failed")).format(new Object[]{file2, file}));
                            }
                        } else {
                            error(new MessageFormat(rb.getString("attempt.to.rename.jarFile.to.origJar.failed")).format(new Object[]{file, file3}));
                        }
                    }
                    displayMessagesAndResult(true);
                } finally {
                }
            } finally {
            }
        } catch (Throwable th3) {
            if (this.zipFile != null) {
                this.zipFile.close();
                this.zipFile = null;
            }
            if (zipOutputStream != null) {
                zipOutputStream.close();
            }
            throw th3;
        }
    }

    static String getDefaultSigAlgForKey(String str) {
        if (str.equalsIgnoreCase("DSA")) {
            return "SHA256withDSA";
        }
        if (str.equalsIgnoreCase("RSA")) {
            return "SHA256withRSA";
        }
        if (str.equalsIgnoreCase("EC")) {
            return "SHA256withECDSA";
        }
        throw new RuntimeException("private key is not a DSA or RSA key");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0012. Please report as an issue. */
    private int findHeaderEnd(byte[] bArr) {
        boolean z;
        boolean z2 = true;
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            switch (bArr[i]) {
                case 13:
                    if (i < length - 1 && bArr[i + 1] == 10) {
                        i++;
                    }
                    break;
                case 10:
                    if (z2) {
                        return i + 1;
                    }
                    z = true;
                    z2 = z;
                    i++;
                default:
                    z = false;
                    z2 = z;
                    i++;
            }
        }
        return length;
    }

    private boolean signatureRelated(String str) {
        return SignatureFileVerifier.isSigningRelated(str);
    }

    private String signerInfo(CodeSigner codeSigner, String str) throws Exception {
        if (this.cacheForSignerInfo.containsKey(codeSigner)) {
            return this.cacheForSignerInfo.get(codeSigner);
        }
        List<? extends Certificate> certificates = codeSigner.getSignerCertPath().getCertificates();
        Timestamp timestamp = codeSigner.getTimestamp();
        String str2 = "";
        if (timestamp != null) {
            str2 = printTimestamp(str, timestamp) + "\n";
        }
        String certsAndTSInfo = certsAndTSInfo(str, str, certificates, timestamp);
        this.cacheForSignerInfo.put(codeSigner, str2 + certsAndTSInfo);
        return certsAndTSInfo;
    }

    private String certsAndTSInfo(String str, String str2, List<? extends Certificate> list, Timestamp timestamp) throws Exception {
        Date date;
        if (timestamp != null) {
            date = timestamp.getTimestamp();
            this.noTimestamp = false;
        } else {
            date = null;
        }
        boolean z = true;
        StringBuilder sb = new StringBuilder();
        sb.append(str).append(rb.getString("...Signer")).append('\n');
        Iterator<? extends Certificate> it = list.iterator();
        while (it.hasNext()) {
            sb.append(printCert(false, str2, it.next(), date, z));
            sb.append('\n');
            z = false;
        }
        try {
            validateCertChain("code signing", list, timestamp);
        } catch (Exception e) {
            this.chainNotValidated = true;
            this.chainNotValidatedReason = e;
            sb.append(str2).append(rb.getString(".Invalid.certificate.chain.")).append(e.getLocalizedMessage()).append("]\n");
        }
        if (timestamp != null) {
            sb.append(str).append(rb.getString("...TSA")).append('\n');
            Iterator<? extends Certificate> it2 = timestamp.getSignerCertPath().getCertificates().iterator();
            while (it2.hasNext()) {
                sb.append(printCert(true, str2, it2.next(), null, false));
                sb.append('\n');
            }
            try {
                validateCertChain("tsa server", timestamp.getSignerCertPath().getCertificates(), null);
            } catch (Exception e2) {
                this.tsaChainNotValidated = true;
                this.tsaChainNotValidatedReason = e2;
                sb.append(str2).append(rb.getString(".Invalid.TSA.certificate.chain.")).append(e2.getLocalizedMessage()).append("]\n");
            }
        }
        if (list.size() == 1 && KeyStoreUtil.isSelfSigned((X509Certificate) list.get(0))) {
            this.signerSelfSigned = true;
        }
        return sb.toString();
    }

    private void writeEntry(ZipFile zipFile, ZipOutputStream zipOutputStream, ZipEntry zipEntry) throws IOException {
        ZipEntry zipEntry2 = new ZipEntry(zipEntry.getName());
        zipEntry2.setMethod(zipEntry.getMethod());
        zipEntry2.setTime(zipEntry.getTime());
        zipEntry2.setComment(zipEntry.getComment());
        zipEntry2.setExtra(zipEntry.getExtra());
        if (zipEntry.getMethod() == 0) {
            zipEntry2.setSize(zipEntry.getSize());
            zipEntry2.setCrc(zipEntry.getCrc());
        }
        zipOutputStream.putNextEntry(zipEntry2);
        writeBytes(zipFile, zipEntry, zipOutputStream);
    }

    private synchronized void writeBytes(ZipFile zipFile, ZipEntry zipEntry, ZipOutputStream zipOutputStream) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = zipFile.getInputStream(zipEntry);
            long size = zipEntry.getSize();
            while (size > 0) {
                int read = inputStream.read(this.buffer, 0, this.buffer.length);
                if (read == -1) {
                    break;
                }
                zipOutputStream.write(this.buffer, 0, read);
                size -= read;
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    void loadKeyStore(String str, boolean z) {
        URL url;
        if (!this.nullStream && str == null) {
            str = System.getProperty("user.home") + File.separator + ".keystore";
        }
        try {
            try {
                KeyStore cacertsKeyStore = KeyStoreUtil.getCacertsKeyStore();
                if (cacertsKeyStore != null) {
                    Enumeration<String> aliases = cacertsKeyStore.aliases();
                    while (aliases.hasMoreElements()) {
                        try {
                            this.trustedCerts.add((X509Certificate) cacertsKeyStore.getCertificate(aliases.nextElement()));
                        } catch (Exception e) {
                        }
                    }
                }
            } catch (Exception e2) {
            }
            if (this.providerName == null) {
                this.store = KeyStore.getInstance(this.storetype);
            } else {
                this.store = KeyStore.getInstance(this.storetype, this.providerName);
            }
            if (this.token && this.storepass == null && !this.protectedPath && !KeyStoreUtil.isWindowsKeyStore(this.storetype)) {
                this.storepass = getPass(rb.getString("Enter.Passphrase.for.keystore."));
            } else if (!this.token && this.storepass == null && z) {
                this.storepass = getPass(rb.getString("Enter.Passphrase.for.keystore."));
            }
            try {
                if (this.nullStream) {
                    this.store.load(null, this.storepass);
                } else {
                    String replace = str.replace(File.separatorChar, '/');
                    try {
                        url = new URL(replace);
                    } catch (MalformedURLException e3) {
                        url = new File(replace).toURI().toURL();
                    }
                    InputStream inputStream = null;
                    try {
                        inputStream = url.openStream();
                        this.store.load(inputStream, this.storepass);
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (Throwable th) {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        throw th;
                    }
                }
                Enumeration<String> aliases2 = this.store.aliases();
                while (aliases2.hasMoreElements()) {
                    String nextElement = aliases2.nextElement();
                    try {
                        X509Certificate x509Certificate = (X509Certificate) this.store.getCertificate(nextElement);
                        if (this.store.isCertificateEntry(nextElement) || x509Certificate.getSubjectDN().equals(x509Certificate.getIssuerDN())) {
                            this.trustedCerts.add(x509Certificate);
                        }
                    } catch (Exception e4) {
                    }
                }
            } finally {
                try {
                    this.pkixParameters = new PKIXBuilderParameters((Set<TrustAnchor>) this.trustedCerts.stream().map(x509Certificate2 -> {
                        return new TrustAnchor(x509Certificate2, null);
                    }).collect(Collectors.toSet()), (CertSelector) null);
                    this.pkixParameters.setRevocationEnabled(false);
                } catch (InvalidAlgorithmParameterException e5) {
                }
            }
        } catch (IOException e6) {
            throw new RuntimeException(rb.getString("keystore.load.") + e6.getMessage());
        } catch (KeyStoreException e7) {
            throw new RuntimeException(rb.getString("unable.to.instantiate.keystore.class.") + e7.getMessage());
        } catch (NoSuchAlgorithmException e8) {
            throw new RuntimeException(rb.getString("keystore.load.") + e8.getMessage());
        } catch (NoSuchProviderException e9) {
            throw new RuntimeException(rb.getString("keystore.load.") + e9.getMessage());
        } catch (CertificateException e10) {
            throw new RuntimeException(rb.getString("certificate.exception.") + e10.getMessage());
        }
    }

    X509Certificate getTsaCert(String str) {
        Object obj = null;
        try {
            obj = this.store.getCertificate(str);
        } catch (KeyStoreException e) {
        }
        if (obj == null || !(obj instanceof X509Certificate)) {
            error(new MessageFormat(rb.getString("Certificate.not.found.for.alias.alias.must.reference.a.valid.KeyStore.entry.containing.an.X.509.public.key.certificate.for.the")).format(new Object[]{str, str}));
        }
        return (X509Certificate) obj;
    }

    void checkCertUsage(X509Certificate x509Certificate, boolean[] zArr) {
        if (zArr != null) {
            zArr[2] = false;
            zArr[1] = false;
            zArr[0] = false;
        }
        boolean[] keyUsage = x509Certificate.getKeyUsage();
        if (keyUsage != null) {
            boolean[] copyOf = Arrays.copyOf(keyUsage, 9);
            if (!copyOf[0] && !copyOf[1] && zArr != null) {
                zArr[0] = true;
                this.badKeyUsage = true;
            }
        }
        try {
            List<String> extendedKeyUsage = x509Certificate.getExtendedKeyUsage();
            if (extendedKeyUsage != null && !extendedKeyUsage.contains("2.5.29.37.0") && !extendedKeyUsage.contains("1.3.6.1.5.5.7.3.3") && zArr != null) {
                zArr[1] = true;
                this.badExtendedKeyUsage = true;
            }
        } catch (CertificateParsingException e) {
        }
        try {
            byte[] extensionValue = x509Certificate.getExtensionValue("2.16.840.1.113730.1.1");
            if (extensionValue != null && !new NetscapeCertTypeExtension(new DerValue(new DerInputStream(extensionValue).getOctetString()).getUnalignedBitString().toByteArray()).get("object_signing").booleanValue() && zArr != null) {
                zArr[2] = true;
                this.badNetscapeCertType = true;
            }
        } catch (IOException e2) {
        }
    }

    void getAliasInfo(String str) throws Exception {
        Object obj = null;
        try {
            Certificate[] certificateArr = null;
            if (this.altCertChain != null) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(this.altCertChain);
                    Throwable th = null;
                    try {
                        certificateArr = (Certificate[]) CertificateFactory.getInstance("X.509").generateCertificates(fileInputStream).toArray(new Certificate[0]);
                        if (fileInputStream != null) {
                            if (0 != 0) {
                                try {
                                    fileInputStream.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            } else {
                                fileInputStream.close();
                            }
                        }
                    } catch (Throwable th3) {
                        if (fileInputStream != null) {
                            if (0 != 0) {
                                try {
                                    fileInputStream.close();
                                } catch (Throwable th4) {
                                    th.addSuppressed(th4);
                                }
                            } else {
                                fileInputStream.close();
                            }
                        }
                        throw th3;
                    }
                } catch (FileNotFoundException e) {
                    error(rb.getString("File.specified.by.certchain.does.not.exist"));
                } catch (IOException | CertificateException e2) {
                    error(rb.getString("Cannot.restore.certchain.from.file.specified"));
                }
            } else {
                try {
                    certificateArr = this.store.getCertificateChain(str);
                } catch (KeyStoreException e3) {
                }
            }
            if (certificateArr == null || certificateArr.length == 0) {
                if (this.altCertChain != null) {
                    error(rb.getString("Certificate.chain.not.found.in.the.file.specified."));
                } else {
                    error(new MessageFormat(rb.getString("Certificate.chain.not.found.for.alias.alias.must.reference.a.valid.KeyStore.key.entry.containing.a.private.key.and")).format(new Object[]{str, str}));
                }
            }
            this.certChain = new X509Certificate[certificateArr.length];
            for (int i = 0; i < certificateArr.length; i++) {
                if (!(certificateArr[i] instanceof X509Certificate)) {
                    error(rb.getString("found.non.X.509.certificate.in.signer.s.chain"));
                }
                this.certChain[i] = (X509Certificate) certificateArr[i];
            }
            try {
                if (!this.token && this.keypass == null) {
                    obj = this.store.getKey(str, this.storepass);
                } else {
                    obj = this.store.getKey(str, this.keypass);
                }
            } catch (UnrecoverableKeyException e4) {
                if (this.token) {
                    throw e4;
                }
                if (this.keypass == null) {
                    this.keypass = getPass(new MessageFormat(rb.getString("Enter.key.password.for.alias.")).format(new Object[]{str}));
                    obj = this.store.getKey(str, this.keypass);
                }
            }
        } catch (KeyStoreException e5) {
        } catch (NoSuchAlgorithmException e6) {
            error(e6.getMessage());
        } catch (UnrecoverableKeyException e7) {
            error(rb.getString("unable.to.recover.key.from.keystore"));
        }
        if (!(obj instanceof PrivateKey)) {
            error(new MessageFormat(rb.getString("key.associated.with.alias.not.a.private.key")).format(new Object[]{str}));
        } else {
            this.privateKey = (PrivateKey) obj;
        }
    }

    void error(String str) {
        System.out.println(rb.getString("jarsigner.") + str);
        System.exit(1);
    }

    void error(String str, Exception exc) {
        System.out.println(rb.getString("jarsigner.") + str);
        if (this.debug) {
            exc.printStackTrace();
        }
        System.exit(1);
    }

    void validateCertChain(String str, List<? extends Certificate> list, Timestamp timestamp) throws Exception {
        try {
            Validator.getInstance("PKIX", str, this.pkixParameters).validate((X509Certificate[]) list.toArray(new X509Certificate[list.size()]), (Collection) null, timestamp);
        } catch (Exception e) {
            e = e;
            if (this.debug) {
                e.printStackTrace();
            }
            if (str.equals("tsa server") && (e instanceof ValidatorException) && e.getCause() != null && (e.getCause() instanceof CertPathValidatorException)) {
                e = (Exception) e.getCause();
                if ((e.getCause() instanceof CertificateExpiredException) && this.hasExpiredTsaCert) {
                    return;
                }
            }
            if (str.equals("code signing") && (e instanceof ValidatorException)) {
                if (e.getCause() != null && (e.getCause() instanceof CertPathValidatorException)) {
                    e = (Exception) e.getCause();
                    Throwable cause = e.getCause();
                    if ((cause instanceof CertificateExpiredException) && this.hasExpiredCert) {
                        return;
                    }
                    if ((cause instanceof CertificateNotYetValidException) && this.notYetValidCert) {
                        return;
                    }
                }
                if ((e instanceof ValidatorException) && ((ValidatorException) e).getErrorType() == ValidatorException.T_EE_EXTENSIONS && (this.badKeyUsage || this.badExtendedKeyUsage || this.badNetscapeCertType)) {
                    return;
                }
            }
            throw e;
        }
    }

    char[] getPass(String str) {
        System.err.print(str);
        System.err.flush();
        try {
            char[] readPassword = Password.readPassword(System.in);
            if (readPassword == null) {
                error(rb.getString("you.must.enter.key.password"));
                return null;
            }
            return readPassword;
        } catch (IOException e) {
            error(rb.getString("unable.to.read.password.") + e.getMessage());
            return null;
        }
    }

    private synchronized byte[] getBytes(ZipFile zipFile, ZipEntry zipEntry) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = zipFile.getInputStream(zipEntry);
            this.baos.reset();
            long size = zipEntry.getSize();
            while (size > 0) {
                int read = inputStream.read(this.buffer, 0, this.buffer.length);
                if (read == -1) {
                    break;
                }
                this.baos.write(this.buffer, 0, read);
                size -= read;
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return this.baos.toByteArray();
        } catch (Throwable th) {
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    private ZipEntry getManifestFile(ZipFile zipFile) {
        ZipEntry entry = zipFile.getEntry("META-INF/MANIFEST.MF");
        if (entry == null) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements() && entry == null) {
                entry = entries.nextElement();
                if (!"META-INF/MANIFEST.MF".equalsIgnoreCase(entry.getName())) {
                    entry = null;
                }
            }
        }
        return entry;
    }

    /* JADX WARN: Finally extract failed */
    private synchronized String[] getDigests(ZipEntry zipEntry, ZipFile zipFile, MessageDigest[] messageDigestArr) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = zipFile.getInputStream(zipEntry);
            long size = zipEntry.getSize();
            while (size > 0) {
                int read = inputStream.read(this.buffer, 0, this.buffer.length);
                if (read == -1) {
                    break;
                }
                for (MessageDigest messageDigest : messageDigestArr) {
                    messageDigest.update(this.buffer, 0, read);
                }
                size -= read;
            }
            if (inputStream != null) {
                inputStream.close();
            }
            String[] strArr = new String[messageDigestArr.length];
            for (int i = 0; i < messageDigestArr.length; i++) {
                strArr[i] = Base64.getEncoder().encodeToString(messageDigestArr[i].digest());
            }
            return strArr;
        } catch (Throwable th) {
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    private Attributes getDigestAttributes(ZipEntry zipEntry, ZipFile zipFile, MessageDigest[] messageDigestArr) throws IOException {
        String[] digests = getDigests(zipEntry, zipFile, messageDigestArr);
        Attributes attributes = new Attributes();
        for (int i = 0; i < messageDigestArr.length; i++) {
            attributes.putValue(messageDigestArr[i].getAlgorithm() + "-Digest", digests[i]);
        }
        return attributes;
    }

    private boolean updateDigests(ZipEntry zipEntry, ZipFile zipFile, MessageDigest[] messageDigestArr, Manifest manifest) throws IOException {
        boolean z = false;
        Attributes attributes = manifest.getAttributes(zipEntry.getName());
        String[] digests = getDigests(zipEntry, zipFile, messageDigestArr);
        for (int i = 0; i < messageDigestArr.length; i++) {
            String str = null;
            try {
                AlgorithmId algorithmId = AlgorithmId.get(messageDigestArr[i].getAlgorithm());
                Iterator<Object> it = attributes.keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object next = it.next();
                    if (next instanceof Attributes.Name) {
                        String name = ((Attributes.Name) next).toString();
                        if (name.toUpperCase(Locale.ENGLISH).endsWith("-DIGEST") && AlgorithmId.get(name.substring(0, name.length() - 7)).equals(algorithmId)) {
                            str = name;
                            break;
                        }
                    }
                }
            } catch (NoSuchAlgorithmException e) {
            }
            if (str == null) {
                attributes.putValue(messageDigestArr[i].getAlgorithm() + "-Digest", digests[i]);
                z = true;
            } else if (!attributes.getValue(str).equalsIgnoreCase(digests[i])) {
                attributes.putValue(str, digests[i]);
                z = true;
            }
        }
        return z;
    }

    private ContentSigner loadSigningMechanism(String str, String str2) throws Exception {
        Class<?> loadClass = new URLClassLoader(PathList.pathToURLs(PathList.appendPath(str2, PathList.appendPath(System.getProperty("java.class.path"), PathList.appendPath(System.getProperty("env.class.path"), (String) null))))).loadClass(str);
        Object newInstance = loadClass.newInstance();
        if (!(newInstance instanceof ContentSigner)) {
            throw new IllegalArgumentException(new MessageFormat(rb.getString("signerClass.is.not.a.signing.mechanism")).format(new Object[]{loadClass.getName()}));
        }
        return (ContentSigner) newInstance;
    }
}
