package org.multibit.hd.core.services;

import org.junit.Before;
import org.multibit.hd.core.managers.InstallationManager;

import java.io.IOException;

public class BitcoinNetworkServiceTest {

  private BitcoinNetworkService bitcoinNetworkService;

  private String applicationDataDirectoryName = InstallationManager.createApplicationDataDirectory();

  @Before
  public void setUp() throws IOException {
    CoreServices.main(null);

    bitcoinNetworkService = CoreServices.newBitcoinNetworkService();

    applicationDataDirectoryName = InstallationManager.createApplicationDataDirectory();
  }


//  @Test
//  public void testSimple() throws Exception {
//    assertThat(bitcoinNetworkService).isNotNull();
//
//    WalletManager walletManager = new WalletManager();
//    walletManager.createWallet("password");
//
//    bitcoinNetworkService.start();
//    bitcoinNetworkService.downloadBlockChain();
//
//    Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);
//
//    bitcoinNetworkService.stopAndWait();
//  }
}
