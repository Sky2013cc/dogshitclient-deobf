package sun.tools.jstatd;

import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:sun/tools/jstatd/Jstatd.class */
public class Jstatd {
    private static Registry registry;
    private static int port = -1;
    private static boolean startRegistry = true;

    private static void printUsage() {
        System.err.println("usage: jstatd [-nr] [-p port] [-n rminame]");
    }

    static void bind(String str, RemoteHostImpl remoteHostImpl) throws RemoteException, MalformedURLException, Exception {
        try {
            Naming.rebind(str, remoteHostImpl);
        } catch (ConnectException e) {
            if (startRegistry && registry == null) {
                registry = LocateRegistry.createRegistry(port < 0 ? 1099 : port);
                bind(str, remoteHostImpl);
            } else {
                System.out.println("Could not contact registry\n" + e.getMessage());
                e.printStackTrace();
            }
        } catch (RemoteException e2) {
            System.err.println("Could not bind " + str + " to RMI Registry");
            e2.printStackTrace();
        }
    }

    public static void main(String[] strArr) {
        String str = null;
        int i = 0;
        while (i < strArr.length && strArr[i].startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
            String str2 = strArr[i];
            if (str2.compareTo("-nr") == 0) {
                startRegistry = false;
            } else if (str2.startsWith("-p")) {
                if (str2.compareTo("-p") != 0) {
                    port = Integer.parseInt(str2.substring(2));
                } else {
                    i++;
                    if (i >= strArr.length) {
                        printUsage();
                        System.exit(1);
                    }
                    port = Integer.parseInt(strArr[i]);
                }
            } else if (str2.startsWith("-n")) {
                if (str2.compareTo("-n") != 0) {
                    str = str2.substring(2);
                } else {
                    i++;
                    if (i >= strArr.length) {
                        printUsage();
                        System.exit(1);
                    }
                    str = strArr[i];
                }
            } else {
                printUsage();
                System.exit(1);
            }
            i++;
        }
        if (i < strArr.length) {
            printUsage();
            System.exit(1);
        }
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        StringBuilder sb = new StringBuilder();
        if (port >= 0) {
            sb.append("//:").append(port);
        }
        if (str == null) {
            str = "JStatRemoteHost";
        }
        sb.append(RuntimeConstants.SIG_PACKAGE).append(str);
        try {
            System.setProperty("java.rmi.server.ignoreSubClasses", Constants.TRUE);
            RemoteHostImpl remoteHostImpl = new RemoteHostImpl();
            bind(sb.toString(), remoteHostImpl);
        } catch (ConnectException e) {
            System.out.println("Could not contact RMI registry\n" + e.getMessage());
            System.exit(1);
        } catch (MalformedURLException e2) {
            if (str != null) {
                System.out.println("Bad RMI server name: " + str);
            } else {
                System.out.println("Bad RMI URL: " + ((Object) sb) + " : " + e2.getMessage());
            }
            System.exit(1);
        } catch (Exception e3) {
            System.out.println("Could not create remote object\n" + e3.getMessage());
            e3.printStackTrace();
            System.exit(1);
        }
    }
}
