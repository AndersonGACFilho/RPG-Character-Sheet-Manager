import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FeaturesRoutingModule } from './features-routing.module';
import { JogosHomeComponent } from './home/home.component';
import { MenuModule } from '../shared/shared.module';

@NgModule({
  declarations: [JogosHomeComponent],
  imports: [
    CommonModule,
    FeaturesRoutingModule,
    MenuModule,
  ],
})
export class FeaturesModule {}
