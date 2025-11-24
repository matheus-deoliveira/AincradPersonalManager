import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Player } from '../models/player.model';
import { Quest } from '../models/quest.model';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  // URL do seu Backend Java
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  // --- PLAYER ---
  // Por enquanto vamos pegar o Player 1 fixo ou passar ID
  getPlayer(id: number): Observable<Player> {
    // Como não criamos endpoint GET /players/{id} no Java ainda, 
    // vamos ter que improvisar ou criar lá rapidinho.
    // DICA: Lembra que o Java precisa ter o GET /players/{id}?
    // Se não tiver, o Angular vai dar erro 404.
    return this.http.get<Player>(`${this.apiUrl}/players/${id}`);
  }

  // --- QUESTS ---
  getQuests(): Observable<Quest[]> {
    return this.http.get<Quest[]>(`${this.apiUrl}/quests`);
  }

  createQuest(quest: Quest, playerId: number): Observable<Quest> {
    return this.http.post<Quest>(`${this.apiUrl}/quests?playerId=${playerId}`, quest);
  }

  completeQuest(questId: number): Observable<Quest> {
    return this.http.put<Quest>(`${this.apiUrl}/quests/${questId}/complete`, {});
  }
}