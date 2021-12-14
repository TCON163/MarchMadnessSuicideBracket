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
    public static username:string;
    public static email:string;
    public static firstName: string;
    public static lastName: string;
    public static playerId: number;
}