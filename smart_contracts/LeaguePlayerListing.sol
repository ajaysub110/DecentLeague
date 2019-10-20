pragma solidity ^0.4.25;

contract PlayerListing
{
enum StateType { PlayerAvailable, PlayerSold }

StateType public State;

address public Seller;
address public InstanceBuyer;
address public ParentContract;
string public PlayerName;
int public PlayerPrice;
address public TeamA;
address public TeamB;


constructor(string playerName, int playerPrice, address seller, address parentContractAddress, address teamA, address teamB) public {
        Seller = seller;
        ParentContract = parentContractAddress;
        PlayerName = playerName;
        PlayerPrice = playerPrice;

        TeamA = teamA;
        TeamB = teamB;

        State = StateType.PlayerAvailable;
}

function BuyPlayer() public
{
    InstanceBuyer = msg.sender;

    // ensure that the buyer is not the seller
    if (Seller == InstanceBuyer) {
        revert();
    }

    LeaguePlayerListing league = LeaguePlayerListing(ParentContract);

    // check Buyer's balance
    if (!league.HasBalance(InstanceBuyer, PlayerPrice)) {
        revert();
    }

    // indicate Player bought by updating seller and buyer balances
    league.UpdateBalance(Seller, InstanceBuyer, PlayerPrice);

    State = StateType.PlayerSold;
    }
}


contract LeaguePlayerListing
{
    enum StateType { TeamProvisioned, PlayerListed, CurrentSaleFinalized}

    StateType public State;

    address public InstanceTeamA;
    int public TeamABalance;

    address public InstanceTeamB;
    int public TeamBBalance;

    address public InstanceLeagueMaintainer;
    address public CurrentSeller;

    string public PlayerName;
    int public PlayerPrice;

    PlayerListing currentPlayerListing;
    address public CurrentContractAddress;

    constructor(address teamA, int balanceA, address teamB, int balanceB) public {
        InstanceLeagueMaintainer = msg.sender;

        // ensure the two parties are different
        if (teamA == teamB) {
            revert();
        }

        InstanceTeamA = teamA;
        TeamABalance = balanceA;

        InstanceTeamB = teamB;
        TeamBBalance = balanceB;

        CurrentContractAddress = address(this);

        State = StateType.TeamProvisioned;
    }

    function HasBalance(address buyer, int PlayerPrice) public view returns (bool) {
        if (buyer == InstanceTeamA) {
            return (TeamABalance >= PlayerPrice);
        }

        if (buyer == InstanceTeamB) {
            return (TeamBBalance >= PlayerPrice);
        }

        return false;
    }

    function UpdateBalance(address sellerTeam, address buyerTeam, int PlayerPrice) public {
        ChangeBalance(sellerTeam, PlayerPrice);
        ChangeBalance(buyerTeam, -PlayerPrice);

        State = StateType.CurrentSaleFinalized;
    }

    function ChangeBalance(address Team, int balance) public {
        if (Team == InstanceTeamA) {
            TeamABalance += balance;
        }

        if (Team == InstanceTeamB) {
            TeamBBalance += balance;
        }
    }

    function ListPlayer(string PlayerName, int PlayerPrice) public
    {
        CurrentSeller = msg.sender;

        currentPlayerListing = new PlayerListing(PlayerName, PlayerPrice, CurrentSeller, CurrentContractAddress, InstanceTeamA, InstanceTeamB);

        State = StateType.PlayerListed;
    }
}
