/*
 * MyFirstCppProgram.cpp
 *
 *  Created on: May 3, 2020
 *      Author: a
 */

# include <iostream>
using namespace std;

int main() {
	cout << "Hello c++ world" << endl;
}

/*
 * To create a project:
 * 0. File>new>project>c++ project (Don't directly select c++ project)
 * 1. Uncheck the checkmark bottom right "Show project types and Toolchains"..
 * 2. Then in executables, Mingw GCC should appear.
 * 3. Select Mingw GCC
 * 4. RMB on project folder> new>Source File
 * 5. Name the Source File <your file name>.cpp
 * 6. Open the Source File and type the minimum required lines:
 * # include <iostream>
 * using namespace std;
 * (std stands for standard)
 * int main() {}
 *
 * 7. RMB and replace "c:/mingw" in project>properties>MINGW_HOME with:
 * C:\Program Files\mingw-w64\x86_64-8.1.0-posix-seh-rt_v6-rev0\mingw64
 * because I didn't install it at c:/mingw but c:/program files..
 *
 *	Repeat step 7 for every new project.
 */

/*
 * How to run a .cpp file:
 * Source: https://www.youtube.com/watch?v=D2oAUDPWkkA
 * 0. Clean previously compiled files with: Project>Clean
 * 1. Press: Compile = hammer
 * 2. Then press triangle next to run>Local C/C++ Application
 */
