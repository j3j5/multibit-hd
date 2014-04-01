package org.multibit.hd.brit.services;

import com.google.bitcoin.core.Wallet;
import com.google.common.collect.Lists;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.multibit.hd.brit.dto.FeeState;
import org.multibit.hd.brit.dto.MatcherResponse;

import java.math.BigInteger;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 *  <p>Service to provide the following to Payers:<br>
 *  <ul>
 *  <li>perform a lookup to the BRIT server to get the list of Bitcoin addresses fees need to be paid to</li>
 *  <li>provide the details of the next fee to be paid by the Payer</li>
 *  </p>
 *  
 */
public class FeeService {

  private final PGPPublicKey matcherPublicKey;
  private final URL matcherURL;

  public final static BigInteger DEFAULT_FEE_PER_SEND = BigInteger.valueOf(500);    // In satoshi
  public final static int NEXT_SEND_DELTA_LOWER_LIMIT = 20;
  public final static int NEXT_SEND_DELTA_UPPER_LIMIT = 30;


  /**
   * Construct a fee service
   * @param matcherPublicKey The PGP public key of the matcher service to perform exchanges with
   * @param matcherURL the HTTP URL to send PayerRequests to
   */
  public FeeService(PGPPublicKey matcherPublicKey, URL matcherURL) {
    this.matcherPublicKey = matcherPublicKey;
    this.matcherURL = matcherURL;
  }

  /**
   * Perform a BRIT exchange with the Matcher to work out what addresses the Payer should pay to.
   *
   * The caller needs to save the wallet after this call to persist extensions added.
   *
   * @param seed the seed of the Wallet (from which the britWalletId is worked out)
   * @param wallet the wallet to perform the BRIT exchange against
   */
  public void performExchangeWithMatcher(byte[] seed, Wallet wallet) {
    // TODO Exchange with Matcher returns a MatcherResponse
    MatcherResponse matcherResponse = new MatcherResponse(new Date(), Lists.<String>newArrayList());

    // Add the replay date and the Bitcoin addresses to the wallet as extensions

    // The wallet now needs saving but that is out-of-scope for BRIT as it does not
    // know how to persist wallets
  }

  /**
   * Calculate the FeeState for the wallet passed in.
   * This calculates what amount of fee needs paying when.
   *
   * The caller needs to save the wallet after this call to persist extensions added.
   *
   * @param wallet the wallet to calculate the fee state for
   */
  public FeeState calculateFeeState(Wallet wallet) {
    // TODO

    // Iterate over all transactions sorted by date, looking for transaction outputs that send
    // to a fee address.
    // The fee address can be any of:
    // + the hardwired addresses
    // + the Bitcoin addresses in the wallet extension

    // Work out the last fee payment and it's send count

    // Work out the number of sends performed after the last fee payment

    // Work out the amount owed by the Payer to the Redeemer (i.e. the sends that have not been paid for yet

    // Work out the count of the sends at which the next payment will be made

    // Work out the Bitcoin address will be sent to next and add it to the wallet
    return new FeeState(true, getHardwiredFeeAddresses().get(0), 0, 20, DEFAULT_FEE_PER_SEND, BigInteger.ZERO);
  }

  /**
   * Get the List of hardwired fee addresses that will be used if the BRIT Matcher exchange fails
   * @return List of bitcoin addresses to use as hardwired fee addresses
   */
  public List<String> getHardwiredFeeAddresses() {
    // Return the multibit.org donation address
    List<String> hardwiredFeeAddresses = Lists.newArrayList();
    hardwiredFeeAddresses.add("1AhN6rPdrMuKBGFDKR1k9A8SCLYaNgXhty");

    return hardwiredFeeAddresses;
  }
}
