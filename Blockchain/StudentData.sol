//Write a program in solidity to create Student data. Use the following constructs:
// Structures   Arrays    Fallback
//Deploy this as smart contract on Ethereum and Observe the transaction fee and Gas values
________________________________________________________________________________________________

// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract StudentData {
    // Structure for student details
    struct Student {
        uint256 rollNo;
        string name;
        uint256 age;
    }

       // Array to store students
    Student[] public students;

    // Add new student
    function addStudent(uint256 _rollNo, string memory _name, uint256 _age) public {
        Student memory newStudent = Student(_rollNo, _name, _age);
        students.push(newStudent);
    }

    // Get student details by index
    function getStudent(uint256 index) public view returns (uint256, string memory, uint256) {
        require(index < students.length, "Invalid index");
        Student memory s = students[index];
        return (s.rollNo, s.name, s.age);
    }

    // Get total number of students
    function getTotalStudents() public view returns (uint256) {
        return students.length;
    }

    // Fallback function (executed if no function matches)
    fallback() external payable {
        // If someone sends ETH or calls invalid function, just accept silently
    }

    // Receive function to accept ETH (optional)
    receive() external payable {}
}

_______________________________________________________________________________________________
//Steps to run:(Must need:Metamask extension installed)

/*Step 1: Open Remix
Go to https://remix.ethereum.org

Step 2: Create your Solidity file
In the left sidebar, click the “+” icon → New File
Name it StudentData.sol
Paste your code:

Step 3: Compile
Click the Solidity Compiler icon (diamond 💠 on the left)
Make sure Compiler version = 0.8.0 or higher
Click Compile StudentData.sol

Step 4: Connect to MetaMask
Open MetaMask → Switch to Sepolia Test Network
Get test ETH if needed:
 https://sepoliafaucet.com
------------------
In Remix, click the Deploy & Run Transactions icon (Ethereum logo)
------------------
Under “Environment” select Injected Provider - MetaMask
(It will ask MetaMask permission — click Allow)

Step 5: Deploy the contract
Under “Deploy” section, click Deploy
MetaMask will pop up → click Confirm
Wait for a few seconds ⏳
(You’ll see “StudentData” under “Deployed Contracts” section.)
*/








*/