JAVAC       = javac
SOURCES     = $(wildcard  src/main/java/**/**.java)
SRCDIR      = .
OUTDIR      = bin
SUBMDIR     = submission
SUBMFILE    = ffs3415.jar
MAINCLASS   = Fermat
MAINSRCFILE = $(MAINCLASS).java
CLASSPATH   = .:./pj2.jar
TESTSFILE   = ../tests.bats
P           = 512461

test: classes
	cd bin; bats $(TESTSFILE)
run: classes
	cd bin; java -classpath .:../pj2.jar pj2 Fermat $(P)
classes: clean outdir
	$(JAVAC) -d $(OUTDIR) -sourcepath $(SRCDIR) -Xlint $(MAINSRCFILE) -Xdiags:verbose -classpath $(CLASSPATH)
outdir:
	mkdir $(OUTDIR)
clean:
	rm -rf $(OUTDIR)

submission: submclean submdir submsrc submfile
submfile:
	cd $(SUBMDIR); jar cvf $(SUBMFILE) *
submsrc:
	cp $(MAINSRCFILE) $(SUBMDIR)
submdir:
	mkdir $(SUBMDIR)
submclean:
	rm -rf $(SUBMDIR)
