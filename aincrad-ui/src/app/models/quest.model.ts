export interface Quest {
    id?: number; // Opcional pq na criação ainda não tem ID
    title: string;
    description: string;
    difficulty: 'E_RANK' | 'D_RANK' | 'C_RANK' | 'B_RANK' | 'A_RANK' | 'S_RANK';
    status: 'PENDING' | 'COMPLETED';
    xpReward: number;
    colReward: number;
    playerId?: number; // Para enviarmos o ID na criação
}