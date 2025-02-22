<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tic-Tac-Toe</title>
    <style>
        body {
            text-align: center;
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(45deg, #1e3c72, #2a5298);
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            color: #f1f1f1;
        }
        .board {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            grid-template-rows: repeat(3, 1fr);
            gap: 10px;
            width: 320px;
            height: 320px;
            background: rgba(0, 0, 0, 0.2);
            padding: 15px;
            border-radius: 10px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.5);
        }
        .cell {
            width: 100px;
            height: 100px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 2em;
            background: rgba(255, 255, 255, 0.1);
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
            cursor: pointer;
            transition: all 0.3s ease-in-out;
            color: #ffffff;
        }
        .cell:hover {
            background: rgba(255, 255, 255, 0.3);
        }
        .cell.taken {
            cursor: not-allowed;
        }
        #status {
            font-size: 1.5em;
            margin: 20px 0;
            color: #f1f1f1;
        }
        .restart-btn {
            padding: 12px 24px;
            font-size: 1em;
            background: #ffcc00;
            color: black;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: 0.3s;
        }
        .restart-btn:hover {
            background: #e6b800;
        }
    </style>
</head>
<body>
    <h1>Tic-Tac-Toe</h1>
    <div class="board" id="board"></div>
    <p id="status">Player X's turn</p>
    <button class="restart-btn" onclick="restartGame()">Restart Game</button>
    
    <script>
        const board = document.getElementById("board");
        const status = document.getElementById("status");
        let currentPlayer = 'X';
        let gameBoard = Array(3).fill(null).map(() => Array(3).fill(''));

        function checkWin(player) {
            for (let i = 0; i < 3; i++) {
                if (gameBoard[i][0] === player && gameBoard[i][1] === player && gameBoard[i][2] === player) return true;
                if (gameBoard[0][i] === player && gameBoard[1][i] === player && gameBoard[2][i] === player) return true;
            }
            if (gameBoard[0][0] === player && gameBoard[1][1] === player && gameBoard[2][2] === player) return true;
            if (gameBoard[0][2] === player && gameBoard[1][1] === player && gameBoard[2][0] === player) return true;
            return false;
        }

        function handleClick(event) {
            const cell = event.target;
            const row = cell.dataset.row;
            const col = cell.dataset.col;
            
            if (gameBoard[row][col] !== '') return;
            gameBoard[row][col] = currentPlayer;
            cell.textContent = currentPlayer;
            cell.classList.add("taken");

            if (checkWin(currentPlayer)) {
                status.textContent = `🎉 Player ${currentPlayer} has won! 🎉`;
                board.removeEventListener("click", handleClick);
                return;
            }
            
            currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
            status.textContent = `Player ${currentPlayer}'s turn`;
        }

        function restartGame() {
            gameBoard = Array(3).fill(null).map(() => Array(3).fill(''));
            board.innerHTML = "";
            currentPlayer = 'X';
            status.textContent = "Player X's turn";
            createBoard();
        }

        function createBoard() {
            board.addEventListener("click", handleClick);
            for (let i = 0; i < 3; i++) {
                for (let j = 0; j < 3; j++) {
                    const cell = document.createElement("div");
                    cell.classList.add("cell");
                    cell.dataset.row = i;
                    cell.dataset.col = j;
                    board.appendChild(cell);
                }
            }
        }

        createBoard();
    </script>
</body>
</html>
