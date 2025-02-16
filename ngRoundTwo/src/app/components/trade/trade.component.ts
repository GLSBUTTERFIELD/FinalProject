import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { TradeService } from '../../services/trade.service';
import { Trade } from '../../models/trade';

@Component({
  selector: 'app-trade',
  imports: [
    CommonModule
  ],
  templateUrl: './trade.component.html',
  styleUrl: './trade.component.css'
})
export class TradeComponent {

  trades: Trade[] = [];

  constructor(
    private tradeService: TradeService,
  ) {}

}
