# coding=utf-8
import os
import commands

protoc_exe = os.path.abspath(r".\protoc.exe")
protoc_csharp_exe = os.path.abspath(r".\protogen.exe")

proto_src_path = os.path.abspath(r".\Src")

#LUA
pb_output_path = os.path.abspath(r'.\Src\\lua\\')+"\\"
print pb_output_path

#JAVA
java_output_path = os.path.abspath(r'.\Src\\java\\')+"\\"
print java_output_path

#PYTHON
python_output_path = os.path.abspath(r'.\Src\Python\\')+"\\"
print python_output_path

#CPP
cpp_output_path = os.path.abspath(r'.\Src\Cpp\\')+"\\"
print python_output_path

#C#
csharp_output_path = os.path.abspath(r'.\Src\\c#\\')+"\\"
print csharp_output_path

def check_path(p):
	if False == os.path.exists(p):
		os.makedirs(p)


check_path(pb_output_path)
check_path(python_output_path)
check_path(cpp_output_path)
check_path(java_output_path)
check_path(csharp_output_path)


def traverse_folder(path):
	files = os.listdir(path)
	for file in files:
		filepath = os.path.join(path,file)
		if os.path.isdir(filepath):
			traverse_folder(filepath)
		elif os.path.splitext(file)[1] == '.proto':
			f = os.path.join(path,filepath)
			name = os.path.splitext(file)[0] 
			os.system("%s -I%s --descriptor_set_out  %s %s"%(protoc_exe,proto_src_path,pb_output_path + name+".pb",f))
			os.system("%s -I%s --python_out %s %s"%(protoc_exe,proto_src_path,python_output_path,f))
			os.system("%s -I%s --cpp_out %s %s"%(protoc_exe,proto_src_path,cpp_output_path,f))
			os.system("%s -I%s --java_out %s %s"%(protoc_exe,proto_src_path,java_output_path,f))
			os.system("%s -i:%s -o:%s"%(protoc_csharp_exe,f,csharp_output_path+name+".cs"))
			#commands.getoutput("%s -I%s --descriptor_set_out  %s %s"%(protoc_exe,proto_src_path,pb_output_path + name+".pb",f))
			print os.path.join(path,filepath)


try:
	traverse_folder(proto_src_path)

except Exception,ex:
	print ex
