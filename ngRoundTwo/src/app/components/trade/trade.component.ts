import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { TradeService } from '../../services/trade.service';
import { InventoryItem } from '../../models/inventory-item';

@Component({
  selector: 'app-trade',
  imports: [
    CommonModule
  ],
  templateUrl: './trade.component.html',
  styleUrl: './trade.component.css'
})
export class TradeComponent implements OnInit {

  availableItems: InventoryItem[] = [];

  constructor(
    private tradeService: TradeService,
  ) {}

  ngOnInit() {
    this.reload();
  }

  reload() {
    this.tradeService.index().subscribe({
      next: (items) => {
        console.log('Trades loaded:', items);
        this.availableItems = items;
      },
      error: (fail) => {
        console.log('TradeComponent.reload: failed to load trade list.', fail);
      }
    });
  }


}
