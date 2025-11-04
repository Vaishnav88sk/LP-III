//Write a smart contract on a test network, for Bank account of a customer for following operations:
// Deposit money        Withdraw Money     Show balance

________________________________________________________________________________________________________
// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract Bank {
    mapping(address => uint256) private balances;

    // Deposit money into your account
    function deposit() public payable {
        require(msg.value > 0, "Deposit must be greater than 0");
        balances[msg.sender] += msg.value;
    }

    // Withdraw money from your account
    function withdraw(uint256 amount) public {
        require(amount > 0, "Amount must be greater than 0");
        require(balances[msg.sender] >= amount, "Insufficient balance");

        balances[msg.sender] -= amount;
        payable(msg.sender).transfer(amount);
    }

    // Show account balance
    function getBalance() public view returns (uint256) {
        return balances[msg.sender];
    }
}
________________________________________________________________________________________________________________

//Steps to run:
//Must term:MetaMask extension installed

/*Step 1: Open Remix
Go to  https://remix.ethereum.org

Step 2: Create the contract
On the left panel, click “+” → New File
Name it Bank.sol
Paste this code inside:

Step 3: Compile
Click the Solidity Compiler icon (looks like a diamond 💎 on the left sidebar)
Make sure compiler version = 0.8.19
Click Compile Bank.sol

Step 4: Connect MetaMask
Open MetaMask in your browser
Click Network dropdown → select Sepolia Test Network
If you don’t see it, enable it under:
Settings → Advanced → “Show test networks” → ON
Get free test ETH:
Visit https://sepoliafaucet.com
Paste your wallet address → click “Send Me ETH

Step 5: Deploy the contract
In Remix, click the Deploy & Run Transactions icon (the Ethereum logo)
Set Environment → Injected Provider - MetaMask
(It will connect Remix to MetaMask)
Click Deploy
MetaMask pops up → click Confirm
Wait a few seconds  — your contract is now live on Ethereum testnet!

Step 6 : Interact
After deployment:
Under Deployed Contracts, expand Bank
--------------------------------
To deposit ETH:
Enter value in “Value” box (e.g. 0.01)
Click deposit
----------------------------
To check your balance:
Click showMyBalance
----------------------------
To withdraw:
Enter amount in wei (e.g. 10000000000000000 for 0.01 ETH)
Click withdraw
*/
