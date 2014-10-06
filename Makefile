JAVAC     = javac
SOURCES   = $(wildcard  src/main/java/**/**.java)
SRCDIR    = .
OUTDIR    = bin
MAINCLASS = Fermat
MAINFILE  = $(MAINCLASS).java
CLASSPATH = .:./pj2.jar
P         = 512461

test: classes
	cd bin; ../tests
run: classes
	cd bin; java -classpath .:../pj2.jar pj2 Fermat $(P)
classes: clean outdir
	$(JAVAC) -d $(OUTDIR) -sourcepath $(SRCDIR) -Xlint $(MAINFILE) -Xdiags:verbose -classpath $(CLASSPATH)
outdir:
	mkdir $(OUTDIR)
clean:
	rm -rf $(OUTDIR)
