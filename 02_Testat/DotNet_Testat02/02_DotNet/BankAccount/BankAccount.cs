using System.Runtime.InteropServices.WindowsRuntime;
using System.Threading;
using System.Threading.Tasks;

namespace BankAccount
{
    public class BankAccount
    {
        private volatile int _balance = 0;

        public void Deposit(int amount)
        {
            Interlocked.Add(ref _balance, amount);
        }

        public bool Withdraw(int amount)
        {
            if (amount <= _balance)
            {
                Interlocked.Add(ref _balance, -amount);
                return true;
            }

            return false;
        }

        public int GetBalance()
        {
            return _balance;
        }
    }
}