package com.sun.tools.javac.sym;

import com.sun.tools.javac.util.Assert;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.slf4j.Marker;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/javac/sym/Profiles.class */
public abstract class Profiles {
    public abstract int getProfileCount();

    public abstract int getProfile(String str);

    public abstract Set<String> getPackages(int i);

    public static void main(String[] strArr) throws IOException {
        Profiles read = read(new File(strArr[0]));
        if (strArr.length >= 2) {
            TreeMap treeMap = new TreeMap();
            for (int i = 1; i <= 4; i++) {
                treeMap.put(Integer.valueOf(i), new TreeSet());
            }
            for (String str : Files.readAllLines(new File(strArr[1]).toPath(), Charset.defaultCharset())) {
                if (str.endsWith(".class")) {
                    String substring = str.substring(0, str.length() - 6);
                    for (int profile = read.getProfile(substring); profile <= 4; profile++) {
                        ((Set) treeMap.get(Integer.valueOf(profile))).add(substring);
                    }
                }
            }
            for (int i2 = 1; i2 <= 4; i2++) {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(i2 + ".txt"));
                try {
                    Iterator it = ((Set) treeMap.get(Integer.valueOf(i2))).iterator();
                    while (it.hasNext()) {
                        bufferedWriter.write((String) it.next());
                        bufferedWriter.newLine();
                    }
                } finally {
                    bufferedWriter.close();
                }
            }
        }
    }

    public static Profiles read(File file) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        try {
            Properties properties = new Properties();
            properties.load(bufferedInputStream);
            if (properties.containsKey("java/lang/Object")) {
                SimpleProfiles simpleProfiles = new SimpleProfiles(properties);
                bufferedInputStream.close();
                return simpleProfiles;
            }
            MakefileProfiles makefileProfiles = new MakefileProfiles(properties);
            bufferedInputStream.close();
            return makefileProfiles;
        } catch (Throwable th) {
            bufferedInputStream.close();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/sun/tools/javac/sym/Profiles$MakefileProfiles.class */
    public static class MakefileProfiles extends Profiles {
        final Map<String, Package> packages = new TreeMap();
        final int maxProfile = 4;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: target.jar:com/sun/tools/javac/sym/Profiles$MakefileProfiles$Package.class */
        public static class Package {
            final Package parent;
            final String name;
            int profile;
            Map<String, Package> subpackages = new TreeMap();
            Map<String, Integer> includedTypes = new TreeMap();
            Map<String, Integer> excludedTypes = new TreeMap();

            Package(Package r5, String str) {
                this.parent = r5;
                this.name = str;
            }

            int getProfile() {
                return this.parent == null ? this.profile : Math.max(this.parent.getProfile(), this.profile);
            }

            int getProfile(String str) {
                Integer num = this.includedTypes.get(str);
                if (num != null) {
                    return num.intValue();
                }
                Integer num2 = this.includedTypes.get(Marker.ANY_MARKER);
                if (num2 != null) {
                    return num2.intValue();
                }
                Integer num3 = this.excludedTypes.get(str);
                if (num3 != null) {
                    return num3.intValue() + 1;
                }
                Integer num4 = this.excludedTypes.get(Marker.ANY_MARKER);
                if (num4 != null) {
                    return num4.intValue() + 1;
                }
                return getProfile();
            }

            String getName() {
                return this.parent == null ? this.name : this.parent.getName() + RuntimeConstants.SIG_PACKAGE + this.name;
            }

            void getPackages(int i, Set<String> set) {
                int profile = getProfile();
                if (profile != 0 && i >= profile) {
                    set.add(getName());
                }
                Iterator<Package> it = this.subpackages.values().iterator();
                while (it.hasNext()) {
                    it.next().getPackages(i, set);
                }
            }
        }

        MakefileProfiles(Properties properties) {
            boolean z = false;
            int i = 1;
            while (i <= 4) {
                String str = i < 4 ? "PROFILE_" + i : "FULL_JRE";
                String property = properties.getProperty(str + "_RTJAR_INCLUDE_PACKAGES");
                if (property == null) {
                    break;
                }
                for (String str2 : property.substring(1).trim().split("\\s+")) {
                    str2 = str2.endsWith(RuntimeConstants.SIG_PACKAGE) ? str2.substring(0, str2.length() - 1) : str2;
                    if (!z && str2.equals("java/lang")) {
                        z = true;
                    }
                    includePackage(i, str2);
                }
                String property2 = properties.getProperty(str + "_RTJAR_INCLUDE_TYPES");
                if (property2 != null) {
                    for (String str3 : property2.replace("$$", "$").split("\\s+")) {
                        if (str3.endsWith(".class")) {
                            includeType(i, str3.substring(0, str3.length() - 6));
                        }
                    }
                }
                String property3 = properties.getProperty(str + "_RTJAR_EXCLUDE_TYPES");
                if (property3 != null) {
                    for (String str4 : property3.replace("$$", "$").split("\\s+")) {
                        if (str4.endsWith(".class")) {
                            excludeType(i, str4.substring(0, str4.length() - 6));
                        }
                    }
                }
                i++;
            }
            if (z) {
                includePackage(1, "javax/crypto");
            }
        }

        @Override // com.sun.tools.javac.sym.Profiles
        public int getProfileCount() {
            return 4;
        }

        @Override // com.sun.tools.javac.sym.Profiles
        public int getProfile(String str) {
            int lastIndexOf = str.lastIndexOf(RuntimeConstants.SIG_PACKAGE);
            String substring = str.substring(0, lastIndexOf);
            return getPackage(substring).getProfile(str.substring(lastIndexOf + 1));
        }

        @Override // com.sun.tools.javac.sym.Profiles
        public Set<String> getPackages(int i) {
            TreeSet treeSet = new TreeSet();
            Iterator<Package> it = this.packages.values().iterator();
            while (it.hasNext()) {
                it.next().getPackages(i, treeSet);
            }
            return treeSet;
        }

        private void includePackage(int i, String str) {
            Package r0 = getPackage(str);
            Assert.check(r0.profile == 0);
            r0.profile = i;
        }

        private void includeType(int i, String str) {
            int lastIndexOf = str.lastIndexOf(RuntimeConstants.SIG_PACKAGE);
            String substring = str.substring(0, lastIndexOf);
            String substring2 = str.substring(lastIndexOf + 1);
            Package r0 = getPackage(substring);
            Assert.check(!r0.includedTypes.containsKey(substring2));
            r0.includedTypes.put(substring2, Integer.valueOf(i));
        }

        private void excludeType(int i, String str) {
            int lastIndexOf = str.lastIndexOf(RuntimeConstants.SIG_PACKAGE);
            String substring = str.substring(0, lastIndexOf);
            String substring2 = str.substring(lastIndexOf + 1);
            Package r0 = getPackage(substring);
            Assert.check(!r0.excludedTypes.containsKey(substring2));
            r0.excludedTypes.put(substring2, Integer.valueOf(i));
        }

        private Package getPackage(String str) {
            Package r10;
            Map<String, Package> map;
            String substring;
            int lastIndexOf = str.lastIndexOf(RuntimeConstants.SIG_PACKAGE);
            if (lastIndexOf == -1) {
                r10 = null;
                map = this.packages;
                substring = str;
            } else {
                r10 = getPackage(str.substring(0, lastIndexOf));
                map = r10.subpackages;
                substring = str.substring(lastIndexOf + 1);
            }
            Package r13 = map.get(substring);
            if (r13 == null) {
                Package r2 = new Package(r10, substring);
                r13 = r2;
                map.put(substring, r2);
            }
            return r13;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/sun/tools/javac/sym/Profiles$SimpleProfiles.class */
    public static class SimpleProfiles extends Profiles {
        private final Map<String, Integer> map = new HashMap();
        private final int profileCount;

        SimpleProfiles(Properties properties) {
            int i = 0;
            for (Map.Entry entry : properties.entrySet()) {
                String str = (String) entry.getKey();
                int intValue = Integer.valueOf((String) entry.getValue()).intValue();
                this.map.put(str, Integer.valueOf(intValue));
                i = Math.max(i, intValue);
            }
            this.profileCount = i;
        }

        @Override // com.sun.tools.javac.sym.Profiles
        public int getProfileCount() {
            return this.profileCount;
        }

        @Override // com.sun.tools.javac.sym.Profiles
        public int getProfile(String str) {
            return this.map.get(str).intValue();
        }

        @Override // com.sun.tools.javac.sym.Profiles
        public Set<String> getPackages(int i) {
            TreeSet treeSet = new TreeSet();
            for (Map.Entry<String, Integer> entry : this.map.entrySet()) {
                String key = entry.getKey();
                int intValue = entry.getValue().intValue();
                if (key.lastIndexOf(RuntimeConstants.SIG_PACKAGE) > 0 && i >= intValue) {
                    treeSet.add(key);
                }
            }
            return treeSet;
        }
    }
}
