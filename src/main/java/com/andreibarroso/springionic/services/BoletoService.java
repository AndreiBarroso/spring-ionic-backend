package com.andreibarroso.springionic.services;

import com.andreibarroso.springionic.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto (PagamentoComBoleto pagto, Date instanteDoPedido) {
			Calendar ca1 = Calendar.getInstance();
			ca1.setTime(instanteDoPedido);
			ca1.add(Calendar.DAY_OF_MONTH, 7);
			pagto.setDataVencimento(ca1.getTime());
	}


}
