class Seat {

    #number;
    #player;

    constructor(number, player = null) {
        this.#number = number;
        this.#player = player;
    }

    isOccupied() {
        return this.#player != null;
    }

    getPlayerName() {
        return this.#player == null ? "空位" : this.#player.name
    }

    setPlayer(player) {
        this.#player = player;
    }

    render() {
        return `<div class="col">
                    <button class="w-100 py-2 mb-2 btn btn-outline-dark rounded-3">
                    <span class="d-block"> #${this.#number} </span>
                    <span class="d-block">${this.getPlayerName()}</span>
                    <span class="d-block">${this.isOccupied()? "未准备" : "未入座"}</span>
                    </button>
                </div>`;
    }

}