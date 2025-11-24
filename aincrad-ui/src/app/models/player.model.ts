export interface Player {
    id: number;
    nickname: string;
    level: number;
    currentXp: number;
    xpToNextLevel: number;
    hp: number;
    maxHp: number;
    colCurrency: number;
}