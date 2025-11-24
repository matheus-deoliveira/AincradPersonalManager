import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { GameService } from './services/game.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {

  constructor(private gameService: GameService) { }

  ngOnInit() {
    console.log("⚔️ TENTANDO CONEXÃO COM AINCRAD...");

    // Tenta buscar o Player 1 (Kirito)
    this.gameService.getPlayer(1).subscribe({
      next: (player) => {
        console.log("✅ CONECTADO! Player encontrado:", player);
      },
      error: (err) => {
        console.error("❌ ERRO DE CONEXÃO:", err);
      }
    });
  }
}
