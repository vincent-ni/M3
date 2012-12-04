import sys
import os
import imp
import stat
import shutil

#Input
svnDir = ""
destDir = ""

#relative locations
m3SetupRelPath = "/m3setup"
toolsRelPath = "/tools"
libRelPath = "/libs"
srcRelPath = "/src"
buildXmlPath = "/build.xml"


#Input Validation
def checkParams(arguments):
    if (len(arguments) != 3):
        print "Wrong Usage Buddy!!!"
        print "Usage:",arguments[0]," <path_To_The_Svn_Path_of_M3> <path_To_The_Deployment_Directory>"
        sys.exit(-1)

    if (not os.path.isdir(arguments[1])):
        print "Looks like the provided directory:",arguments[1]," doesn't exist"
        print "Please check this and retry"
        sys.exit(-2)

    if(os.path.isdir(arguments[2])):
        print "Warning: The provided target directory:",arguments[2]," exist,contents will be replaced"
    else:
        os.makedirs(arguments[2])

    #Copy the provided data to the global variables
    global svnDir
    global destDir
    svnDir = os.path.abspath(arguments[1])
    destDir = os.path.abspath(arguments[2])
    

#copy src folder to the destination dir
def copyDirectory(srcDir,destDir):    
    if(not os.path.isdir(srcDir)):
        print "Error: Src folder doesn't exist:",srcDir
        return -1

    if (os.path.exists(destDir)):
         shutil.rmtree(destDir,True)
         
    shutil.copytree(srcDir,destDir)
    return 0


#copy file from src to destination
def copyFile(srcFile,destFile):    
    if(not os.path.isfile(srcFile)):
        print "Error: Src File is not present at the location:",srcFile
        return -1

    if (not os.path.exists(os.path.dirname(destFile))):
         os.makedirs(os.path.dirname(destFile))
         
    shutil.copyfile(srcFile,destFile)
    return 0

#generate create emu script
def generateCreateEmuScript(setupfolder):
    freshAvdPath = setupfolder + "/freshavd/emu.avd"
    newAvdPath = "\""+os.path.expanduser("~/.android/avd")+"/$1.avd\""
    newAvdPathWQ = os.path.expanduser("~/.android/avd")+"/$1.avd"
    newIniPath = "\""+os.path.expanduser("~/.android/avd")+"/$1.ini\""
    f = open(setupfolder+"/createemu.sh", 'w')
    f.write("#!/bin/sh\n")
    f.write("mkdir -p "+newAvdPath+"\n")
    f.write("echo \"target=android-10\" > "+newIniPath+"\n")
    f.write("echo \"path="+newAvdPathWQ+"\" >> "+newIniPath+"\n")
    f.write("cp "+freshAvdPath+"/* "+newAvdPath+"/\n")
    f.close()
    os.chmod(setupfolder+"/createemu.sh",stat.S_IRWXU|stat.S_IRWXG|stat.S_IRWXO)
    return setupfolder+"/createemu.sh"

#Main Code
print "       **          * * *          **        *       *\n"
print "     /   \       |       *      /   \       | \   / |\n"
print "    /     \      |       *     /     \      |  \ /  |\n"
print "   / ----- \     |       *    / ----- \     |       |\n"
print "  /         \    |       *   /         \    |       |\n"
print " /           \    * * *     /           \   |       |\n"
checkParams(sys.argv)
sdkInstallPath = os.environ['SDK_INSTALL']

if sdkInstallPath is None:
    print "You need to set SDK_INSTALL environment variable"
    sys.exit(-4)
    
#copy m3setup
if(copyDirectory(svnDir+m3SetupRelPath,destDir+m3SetupRelPath) != 0):
    print "Problem occured while copying m3setup folder"
    sys.exit(-3)
    
#copy src
if(copyDirectory(svnDir+srcRelPath,destDir+srcRelPath) != 0):
    print "Problem occured while copying src folder"
    sys.exit(-3)

#copy libs
if(copyDirectory(svnDir+libRelPath,destDir+libRelPath) != 0):
    print "Problem occured while copying lib folder"
    sys.exit(-3)

#copy tools
if(copyDirectory(svnDir+toolsRelPath,destDir+toolsRelPath) != 0):
    print "Problem occured while copying tools folder"
    sys.exit(-3)

#copy build.xml
if(copyFile(svnDir+buildXmlPath,destDir+buildXmlPath) != 0):
    print "Problem occured while copying build.xml"
    sys.exit(-3)


createEmuScript = generateCreateEmuScript(destDir+m3SetupRelPath)
avdPath = os.path.expanduser("~/.android/avd")
#generate m3.properties
f = open(destDir+"/m3.properties", 'w')
f.write("work_dir="+destDir+"/workingDir\n")
f.write("sdk_install="+sdkInstallPath+"\n")
f.write("app_dir="+destDir+"/apps\n")
f.write("instru_setup="+destDir+"/m3setup/instrumentation\n")
f.write("test_strategy=WidgetBasedTesting\n")
f.write("sel_stra=RandomBiasBased\n")
f.write("max_widgets=10\n")
f.write("ker_mod="+destDir+"/m3setup/kernelfiles\n")
f.write("cov_sam=100\n")
f.write("create_emu="+createEmuScript+"\n")
f.write("avd_store="+avdPath+"\n")
f.write("event_count=100,1000\n")
f.write("apktool_loc="+destDir+"/tools/apktool/apktool.jar\n")
f.write("tools_dir="+destDir+"/tools/\n")
f.write("manual_mode=0\n")
f.write("max_emu=16\n")
f.write("system_image="+destDir+"/m3setup/customimage/system.img\n")
f.write("ramdisk_image="+destDir+"/m3setup/customimage/ramdisk.img\n")
f.write("monkeyrunner_script="+destDir+"/m3setup/monkeyrunner/monkeyrunner.py\n")
f.write("complete_notify=dynodroid@googlegroups.com\n")

#Below are the settings used by the underlying RMI engine
#DBConnection String in form of : server;dbname;username;password
f.write("rmi_db=localhost;m3db;root;password\n")

#fully qualified Server name
f.write("apk_srv=pag-www.gtisc.gatech.edu\n")
f.write("res_srv=pag-www.gtisc.gatech.edu\n")
f.write("res_pub_srv=pag-www.gtisc.gatech.edu\n")

#These are paths
f.write("res_dwn=/var/www/pag/internal/machiry/temp_rmi/results/downloadPath\n")
f.write("res_rem_path=/var/www/pag/internal/machiry/temp_rmi/results\n")
f.write("apk_rem_path=/var/www/pag/internal/machiry/temp_rmi\n")

#target for which the apps will be built
f.write("android_target=3\n")

#These are the parameters for results post processing
f.write("post_proc_scr="+destDir+"/m3setup/resultspostprocessing/parser.py\n")
f.write("web_srv_results=http://pag-www.gtisc.gatech.edu/internal\n")

#This the the user name used to do scp
f.write("scp_user_name=machiry")
os.makedirs(destDir+"/apps")
print "Sucess: Deploying m3 to the target folder\n"
print "\tm3.properties have been created\n"
print "\nTHINGS TO DO BEFORE YOU RUN M3\n"
print "\t1)Copy the apps that needs to be tested to the folder:",destDir+"/apps\n"
print "\t2)Modify the required TestStratgey,SelectionStrategy and number of events\n"
print "\t3)Add the text required in to textBoxInput files under the src folder of the app\n"
print "\t4)If you want to manually execise widgets which M3 is unable to test automatically, then set manual_mode=1 in m3.properties\n"
print "\t\tThe format of the file is: <uniqueid>:<textToBeEntered>"
print "\nNote:All Logs will be created in folder:",destDir+"/workingDir\n"
print "\n\nAfter you do all above : Browse to the folder:",destDir," and run : ant clean,ant compile,ant run\n"
print "\n\nWhile its running you probably want to do something else as it takes a bit of time\n"
print "\n\n\tEnjoy Using M3 a.k.a ADAM\n"





