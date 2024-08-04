import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FeaturesRoutingModule } from './features-routing.module';
import { JogosHomeComponent } from './home/home.component';
import { MenuComponent } from '../shared/menu/menu.component';

@NgModule({
  declarations: [JogosHomeComponent, MenuComponent],
  imports: [
    CommonModule,
    FeaturesRoutingModule,
  ]
})
export class FeaturesModule { }
