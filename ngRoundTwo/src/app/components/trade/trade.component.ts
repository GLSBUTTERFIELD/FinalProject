import { InventoryItem } from './../../models/inventory-item';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { TradeService } from '../../services/trade.service';
import { InventoryItemComment } from '../../models/inventory-item-comment';

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
  selected: InventoryItem | null = null;
  itemComments: InventoryItemComment[] = [];

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

  showItem(inventoryItemId: number){
    this.tradeService.getInventoryItemById(inventoryItemId).subscribe({
      next: (inventoryItem) =>{
        this.selected = inventoryItem;
      },
      error: (fail) => {
        console.log('InventoryItemComponent.showInventoryItem: failed to load inventoryItem.', fail);
      },
    })
  }

}
