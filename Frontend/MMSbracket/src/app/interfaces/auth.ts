export class LoginDTO {
    username!: string;
    password!: string;
}

export class Player {
    username!:string;
    email!:string;
    firstName!: string;
    lastName!: string;
    playerId!: number;

}

export class CurrentUser {
    public static username:string | '' | any;
    public static email:string | '' | any;
    public static firstName: string | '' | any;
    public static lastName: string | '' | any;
    public static playerId: number | '' | any;
    public static token: string;
}



export class RegisterPlayer {
    username!:string;
    email!:string;
    firstName!: string;
    lastName!: string;
    playerId!: number;
    password!: string;

}

export class Admin {
    adminId!: number;
    player!: Player;
}
